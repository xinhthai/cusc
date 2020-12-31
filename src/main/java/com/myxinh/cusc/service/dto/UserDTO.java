package com.myxinh.cusc.service.dto;

import com.myxinh.cusc.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO implements Serializable {
    private String userId;
    private String username;
    private String fullName;
    private boolean active;
    private Set<Role> roles;
}
