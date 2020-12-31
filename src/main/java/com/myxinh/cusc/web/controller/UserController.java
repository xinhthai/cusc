package com.myxinh.cusc.web.controller;

import com.myxinh.cusc.service.UserService;
import com.myxinh.cusc.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<UserDTO> getAll(){
        return userService.getAllUser();
    }


}
