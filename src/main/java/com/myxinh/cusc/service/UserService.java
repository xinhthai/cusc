package com.myxinh.cusc.service;

import com.myxinh.cusc.repository.UserRepository;
import com.myxinh.cusc.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
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
                        userEntity.isActive(),userEntity.getRoles()))
                .collect(Collectors.toList());
    }
}
