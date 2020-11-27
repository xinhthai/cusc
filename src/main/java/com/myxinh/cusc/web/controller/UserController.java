package com.myxinh.cusc.web.controller;

import com.myxinh.cusc.security.AuthoritiesConstants;
import com.myxinh.cusc.security.jwt.TokenProvider;
import com.myxinh.cusc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/admin")
//    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
//    @Secured("ADMIN")
    public ResponseEntity<?> getUsers(){
        return new ResponseEntity<>("hello Admin",HttpStatus.OK);
    }

}
