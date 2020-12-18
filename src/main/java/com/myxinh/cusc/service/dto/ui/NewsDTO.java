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
public class NewsDTO {
    private int newsId;
    private String title;
    private String detail;
    private Date createdDate;
    private int menuId;
    private int categoryId;
    private String userId;
    private boolean mainNews;
    private MultipartFile imagePath;

}
