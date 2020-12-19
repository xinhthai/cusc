package com.myxinh.cusc.web.controller;

import com.myxinh.cusc.domain.News;
import com.myxinh.cusc.service.NewsService;
import com.myxinh.cusc.service.dto.ui.NewsUploadDTO;
import com.myxinh.cusc.service.dto.ui.ViewNewsDTO;
import com.myxinh.cusc.web.constants.SystemConstants;
import com.myxinh.cusc.web.errors.BadRequestAlertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NewsController {

    @Value("${uploadDir}")
    private String uploadFolder;

    @Autowired
    private NewsService newsService;

    @PostMapping("/news")//add new Category
    public ResponseEntity<News> saveCategory(@ModelAttribute NewsUploadDTO newsUploadDTO) throws URISyntaxException {
        if (!newsUploadDTO.getNewsId().equals("")) {
            throw new BadRequestAlertException(newsUploadDTO.getNewsId());
        }
        else {
            News newNews = newsService.addNews(newsUploadDTO);
            return ResponseEntity.created(
                    new URI(SystemConstants.BASE_URL+"/categories/"+newsUploadDTO.getNewsId()))
                    .body(newNews);
        }
    }

    @GetMapping("/news/titles")
    public ResponseEntity<List<ViewNewsDTO>> getAllNewsByTitle(){
        return ResponseEntity.ok(newsService.getAllNewsTitleAndId());
    }

}
