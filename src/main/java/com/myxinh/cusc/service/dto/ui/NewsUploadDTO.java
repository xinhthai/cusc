package com.myxinh.cusc.service.dto.ui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewsUploadDTO {
    private String newsId;
    private String title;
    private String detail;
    private Date createdDate;
    private String menuId;
    private String categoryId;
    private String userId;
    private String mainNews;
    private MultipartFile imagePath;
}
