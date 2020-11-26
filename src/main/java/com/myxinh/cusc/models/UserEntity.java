package com.myxinh.cusc.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements Serializable{
    @Id
    private String userId;

    @Column(name = "userName")
    private String userName;
    private String password;
    private String fullName;
    private String sex;
    private String createdDate;

    @Column(name = "lastModifiedDate")
    private String lastModifiedDate;

    private boolean active;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinTable(name="user_role",
            joinColumns = @JoinColumn(name ="userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private List<Role> roles;
}
//API CRUD Spring boot