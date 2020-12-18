package com.myxinh.cusc.web.controller;

import com.myxinh.cusc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public ResponseEntity<?> getUsers(){
        return new ResponseEntity<>("hello Admin",HttpStatus.OK);
    }

}
