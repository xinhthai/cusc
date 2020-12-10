package com.myxinh.cusc.web.controller;

import com.myxinh.cusc.domain.News;
import com.myxinh.cusc.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class NewsController {

    @Value("${uploadDir}")
    private String uploadFolder;

    @Autowired
    private NewsService newsService;

//    @PostMapping("/news")
//    public ResponseEntity<?> addNew(@RequestBody News news, @RequestParam("detail") MultipartFile multipartFile){
//
//    }

}
