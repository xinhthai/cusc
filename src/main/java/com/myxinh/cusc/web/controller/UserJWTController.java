package com.myxinh.cusc.web.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myxinh.cusc.security.jwt.JWTFilter;
import com.myxinh.cusc.security.jwt.TokenProvider;
import com.myxinh.cusc.service.UserService;
import com.myxinh.cusc.service.dto.AuthRequest;
import com.myxinh.cusc.web.errors.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserJWTController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenProvider tokenProvider;


    @PostMapping("/auth")
    public ResponseEntity<JWTToken> authorize(@RequestBody AuthRequest auth){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(auth.getUserName(),auth.getPassword())
            );
            final String jwt = tokenProvider.createToken(authentication);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER,"Bearer "+jwt);
            return new ResponseEntity<>(new JWTToken(jwt),httpHeaders,HttpStatus.OK);
        }catch (UserNotFoundException e){
            throw new UserNotFoundException("Invalid Username and Password");
        }
    }

    static class JWTToken {

        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }

}
