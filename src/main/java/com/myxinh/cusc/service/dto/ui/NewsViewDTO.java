package com.myxinh.cusc.service.dto.ui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewsViewDTO {
    private int newsId;
    private String title;
    private String shortContent;
}
