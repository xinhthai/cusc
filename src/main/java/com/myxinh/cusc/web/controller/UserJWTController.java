package com.myxinh.cusc.web.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myxinh.cusc.domain.Role;
import com.myxinh.cusc.domain.UserEntity;
import com.myxinh.cusc.security.jwt.JWTFilter;
import com.myxinh.cusc.security.jwt.TokenProvider;
import com.myxinh.cusc.service.UserService;
import com.myxinh.cusc.service.dto.LoginRequest;
import com.myxinh.cusc.service.dto.UserDTO;
import com.myxinh.cusc.web.errors.ErrorConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserJWTController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserService userService;


    @PostMapping("/auth")
    public ResponseEntity<Object> authorize(@RequestBody LoginRequest auth){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(auth.getUsername(),auth.getPassword())
            );
            boolean rememberMe = (auth.isRememberMe() == null) ? false : auth.isRememberMe();
            final String jwt = tokenProvider.createToken(authentication, rememberMe);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER,"Bearer "+jwt);
            return new ResponseEntity<>(userService.getUserWithAuthorities()
                    .map(userEntity -> new UserDTO(userEntity.getUsername()
                            ,userEntity.getFullName()
                            ,userEntity.isActive()
                            ,userEntity.getRoles().stream().map(Role::getName).collect(Collectors.toList())
                    )),httpHeaders,HttpStatus.OK);
        }catch (Exception e){
            throw new BadCredentialsException(ErrorConstants.BAD_CREDENTIALS);
        }
    }
}
