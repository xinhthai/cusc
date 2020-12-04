package com.myxinh.cusc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "news")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class News implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_id")
    private int newsId;

    @Column(name = "title",length = 150,nullable = false)
    private String title;

    @Column(name = "description",length = 300,nullable = false)
    private String description;

//    @Column(name = "detail");
//    private String
}
