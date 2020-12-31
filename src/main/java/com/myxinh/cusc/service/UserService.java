package com.myxinh.cusc.service;

import com.myxinh.cusc.domain.Role;
import com.myxinh.cusc.domain.UserEntity;
import com.myxinh.cusc.repository.UserRepository;
import com.myxinh.cusc.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService{

    @Autowired
    UserRepository userRepository;

    public List<UserDTO> getAllUser(){
        return userRepository.findAll()
                .stream()
                .map(userEntity -> new UserDTO(
                        userEntity.getUserId(),userEntity.getUsername(),userEntity.getFullName(),
                        userEntity.isActive(),
                        userEntity
                                .getRoles()
                                .stream()
                                .map(Role::getName)
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }
}
