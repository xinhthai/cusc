package com.myxinh.cusc.repository;

import com.myxinh.cusc.domain.News;
import com.myxinh.cusc.service.dto.ui.NewsViewDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface NewsRepository extends JpaRepository<News, Integer> {

    @Query("SELECT NEW com.myxinh.cusc.service.dto.ui.NewsViewDTO(" +
            "n.newsId,n.title,n.shortContent,n.imagePath,n.category.categoryName,n.status)" +
            " FROM News n")
    List<NewsViewDTO> findAllNewsView();

    @Query("SELECT NEW com.myxinh.cusc.service.dto.ui.NewsViewDTO(" +
            "n.newsId,n.title,n.shortContent,n.imagePath,n.category.categoryName,n.status)" +
            "FROM News n WHERE n.category.categoryId =:categoryId OR n.menu.menuId =:menuId")
    List<NewsViewDTO> findNewsByCategoryIdOrMenuId(@Param("categoryId") int categoryId,@Param("menuId") int menuId);

    @Modifying
    @Query("UPDATE News n SET n.status =:status WHERE n.newsId =: newsId")
    String updateNewStatus(int newsId,String status);
}


