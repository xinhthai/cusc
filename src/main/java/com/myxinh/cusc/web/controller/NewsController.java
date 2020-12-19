package com.myxinh.cusc.web.controller;

import com.myxinh.cusc.domain.News;
import com.myxinh.cusc.repository.NewsRepository;
import com.myxinh.cusc.service.NewsService;
import com.myxinh.cusc.service.dto.ui.NewsUploadDTO;
import com.myxinh.cusc.service.dto.ui.NewsViewDTO;
import com.myxinh.cusc.web.constants.SystemConstants;
import com.myxinh.cusc.web.errors.BadRequestAlertException;
import com.myxinh.cusc.web.errors.ErrorConstants;
import com.myxinh.cusc.web.errors.IsAlreadyException;
import com.myxinh.cusc.web.errors.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private NewsRepository newsRepository;

    @GetMapping("/news/view")// Get View of News
    public ResponseEntity<List<NewsViewDTO>> getAllNewsByTitle(){
        return ResponseEntity.ok(newsService.getAllNewsTitleAndId());
    }

    @GetMapping("/news/{newsId}")// Get News Detail
    public ResponseEntity<News> getNewsById(@PathVariable("newsId") int newsId){
        Optional<News> newsFind = newsService.findById(newsId);
        if (newsFind.isPresent()){
            return ResponseEntity.ok(newsFind.get());
        }else {
            throw new NotFoundException("News "+ ErrorConstants.NOT_FOUND);
        }
    }

    @PostMapping("/news")//Add new News
    public ResponseEntity<News> saveCategory(@ModelAttribute NewsUploadDTO newsUploadDTO) throws URISyntaxException {
        if (!newsUploadDTO.getNewsId().equals("")) {
            throw new BadRequestAlertException(newsUploadDTO.getNewsId());
        }
        else {
            News newNews = newsService.addNews(newsUploadDTO);
            return ResponseEntity.created(
                    new URI(SystemConstants.BASE_URL+"/news/"+newsUploadDTO.getNewsId()))
                    .body(newNews);
        }
    }

    @PutMapping("/news")//Update News existing in database
    public ResponseEntity<Object> updateCategory(@Valid @RequestBody News news) throws URISyntaxException {
        Optional<News> existingNews = newsService.findById(news.getNewsId());
        if (existingNews.isPresent() && !(existingNews.get().getNewsId() == news.getNewsId())){
            throw new IsAlreadyException(news.getTitle());
        }
        Optional<Object> newNews =newsService.updateNews(news);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI(SystemConstants.BASE_URL+"/news/"+news.getNewsId()));
        if (newNews.isPresent()){
            newsRepository.save(news);
            return ResponseEntity.ok(newNews.get());
        }else {
            return ResponseEntity.noContent().headers(headers).build();
        }
    }

    @DeleteMapping("/news/{newsId}")//Delete Category existing in database
    public ResponseEntity<Void> deleteCategory(@PathVariable ("newsId") int newsId) throws URISyntaxException {
        newsService.deleteNews(newsId);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI(SystemConstants.BASE_URL+"/news/"+newsId));
        return ResponseEntity.noContent().headers(headers).build();
    }

}
