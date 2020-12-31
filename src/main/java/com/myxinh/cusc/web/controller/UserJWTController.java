package com.myxinh.cusc.web.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myxinh.cusc.security.jwt.JWTFilter;
import com.myxinh.cusc.security.jwt.TokenProvider;
import com.myxinh.cusc.service.dto.LoginRequest;
import com.myxinh.cusc.web.errors.ErrorConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:4200")
public class UserJWTController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

//    @CrossOrigin(allowedHeaders = {"Authorization"})
//    @CrossOrigin(origins = "*",allowCredentials = ,allowedHeaders = , exposedHeaders = , methods = , value = )
    @PostMapping("/auth")
    public ResponseEntity<JWTToken> authorize(@RequestBody LoginRequest auth){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(auth.getUsername(),auth.getPassword())
            );
            final String jwt = tokenProvider.createToken(authentication, auth.getRememberMe());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER,"Bearer "+jwt);
            return new ResponseEntity<>(new JWTToken(jwt),httpHeaders,HttpStatus.OK);
        }catch (Exception e){
            throw new BadCredentialsException(ErrorConstants.BAD_CREDENTIALS);
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
