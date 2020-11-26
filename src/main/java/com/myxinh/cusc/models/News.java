//package com.myxinh.cusc.models;
//
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Table(name = "news")
//@NoArgsConstructor
//@AllArgsConstructor
//public class News {
//    @Id
//    private int newsId;
//    private String title;
//    private String description;
//    private String detail;
//    private String imagePath;
//    private int categoryId;
//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {CascadeType.MERGE,CascadeType.REFRESH})
//    @JoinTable(name="image",
//            joinColumns = @JoinColumn(name ="newsId"),
//            inverseJoinColumns = @JoinColumn(name = "newsId"))
//    private List<Image> images;
//}
