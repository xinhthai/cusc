package com.myxinh.cusc.service.dto;

import com.myxinh.cusc.domain.Role;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class UserDTO implements Serializable {
    private String userId;
    private String username;
    private String fullName;
    private Set<Role> roles;
}
