package com.myxinh.cusc.web.controller;

import com.myxinh.cusc.service.UserService;
import com.myxinh.cusc.service.dto.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    //URL:localost:3000/api/authenticate
    @PostMapping("/auth")
    public ResponseEntity<?> createAuthenticationToken(HttpSession session,@RequestBody AuthRequest auth){
        ResponseEntity responseEntity = null;
        final UserDetails userDetails = userService.loadUserByUsername(auth.getUserName());
        if (userDetails != null){
            responseEntity = new ResponseEntity<>("SUCCESSFULL", HttpStatus.OK);
        }else {
            responseEntity = new ResponseEntity<>("FAILED", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

}
