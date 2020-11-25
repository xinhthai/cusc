package com.myxinh.cusc.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
class Category {
    @Id
    private int categoryId;
}
