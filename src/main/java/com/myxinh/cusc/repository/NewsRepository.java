package com.myxinh.cusc.repository;

import com.myxinh.cusc.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Integer> {

    @Query("SELECT n.title,n.newsId FROM News n")
    List<News> getAllTitleAndId();
}
