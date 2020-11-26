//package com.myxinh.cusc.models;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Table(name = "category")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class Category {
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int categoryId;
//    private String name;
//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {CascadeType.MERGE,CascadeType.REFRESH})
//    @JoinTable(name="news",
//            joinColumns = @JoinColumn(name = "categoryId"),
//            inverseJoinColumns = @JoinColumn(name = "categoryId"))
//    private List<News> news;
//}
