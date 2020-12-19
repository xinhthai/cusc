package com.myxinh.cusc.repository;

import com.myxinh.cusc.domain.News;
import com.myxinh.cusc.service.dto.ui.NewsViewDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface NewsRepository extends JpaRepository<News, Integer> {

    @Query("SELECT NEW com.myxinh.cusc.service.dto.ui.NewsViewDTO(n.newsId,n.title,n.shortContent) FROM News n")
    List<NewsViewDTO> getNewsIdAndTitle();

}


