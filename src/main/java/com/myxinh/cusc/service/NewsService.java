package com.myxinh.cusc.service;

import com.myxinh.cusc.domain.Category;
import com.myxinh.cusc.domain.News;
import com.myxinh.cusc.repository.MenuRepository;
import com.myxinh.cusc.repository.NewsRepository;
import com.myxinh.cusc.service.dto.ui.NewsDTO;
import com.myxinh.cusc.service.dto.ui.NewsUploadDTO;
import com.myxinh.cusc.service.dto.ui.NewsViewDTO;
import com.myxinh.cusc.service.mapper.MapperUtils;
import com.myxinh.cusc.web.constants.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.*;
import java.util.List;
import java.util.Optional;

@Service
public class NewsService extends MapperUtils<News, NewsDTO>{
    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private MenuRepository menuRepository;

    public News addNews(NewsUploadDTO newsUploadDTO){
        try {
            File file = new File(SystemConstants.IMAGE_DIRECTORY + newsUploadDTO.getImagePath().getOriginalFilename());//;lưu ảnh vào folder dự án
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(newsUploadDTO.getImagePath().getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        News news = new News();
        news.setNewsId(0);
        news.setTitle(newsUploadDTO.getTitle());
        news.setShortContent(newsUploadDTO.getShortContent());
        news.setDetail(newsUploadDTO.getDetail());
        news.setImagePath(newsUploadDTO.getImagePath().getOriginalFilename());
        news.setMainNews(Boolean.parseBoolean(newsUploadDTO.getMainNews()));
        news.setCategory(new Category(Integer.parseInt(newsUploadDTO.getCategoryId()),""));
        news.setMenu(
                Optional.of(menuRepository.findById(Integer.parseInt(newsUploadDTO.getMenuId())))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .orElse(null)
        );
        return newsRepository.save(news);
    }

    public List<NewsViewDTO> getAllNewsView(){
        return newsRepository.findAllNewsView();
    }

    public Optional<News> findById(int id){
        return newsRepository.findById(id);
    }

    public List<NewsViewDTO> getNewsByCategoryIdOrMenuId(int categoryId,int menuId){
        return newsRepository.findNewsByCategoryIdOrMenuId(categoryId,menuId);
    }

    public Optional<Object> updateNews(News news){
        return Optional.of(newsRepository
                .findById(news.getNewsId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(oldNews -> convert(news,NewsDTO.class));
    }

    public void deleteNews(int newsId){
        newsRepository.findById(newsId).ifPresent(
                news -> {newsRepository.delete(news);}
        );
    }

    public void updateNewsStatus(int newsId,boolean status){

        newsRepository.findById(newsId).ifPresent(
                news -> {newsRepository.updateNewStatus(status,newsId);}
        );
    }

}
