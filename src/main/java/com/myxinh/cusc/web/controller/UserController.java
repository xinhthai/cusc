package com.myxinh.cusc.web.controller;

import com.myxinh.cusc.security.AuthoritiesConstants;
import com.myxinh.cusc.security.jwt.JwtTokenProvider;
import com.myxinh.cusc.service.UserService;
import com.myxinh.cusc.service.dto.AuthRequest;
import com.myxinh.cusc.web.errors.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    //URL:localost:3000/api/authenticate
    @PostMapping("/auth")
    public ResponseEntity<?> createAuthenticationToken(HttpSession session,@RequestBody AuthRequest auth){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(auth.getUserName(),auth.getPassword())
            );
        }catch (UserNotFoundException e){
            throw new UserNotFoundException("Invalid Username and Password");
        }
        final UserDetails userDetails = userService.loadUserByUsername(auth.getUserName());
        if (userDetails != null){
            final String token = jwtTokenProvider.generateToken(userDetails);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }else {
            throw new UserNotFoundException("Invalid Username and Password");
        }

    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<?> getUsers(){
        return new ResponseEntity<String>("UNAUTHORIZED",HttpStatus.UNAUTHORIZED);
    }
//    @GetMapping("/users")
//    public ResponseEntity<?> getListUser(){
//        List<UserEntity> users=userService.getUsers();
//        return ResponseEntity.ok(users);
//    }
//    @PostMapping("/users")
//    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity, UriComponentsBuilder builder){
//        userService.createUser(userEntity);
//        HttpHeaders headers=new HttpHeaders();
//        headers.setLocation(builder.path("/users/{id}").buildAndExpand(userEntity.getUserId()).toUri());
//        return new ResponseEntity<>(userEntity,HttpStatus.CREATED);
//    }
//    @PutMapping("/users/{id}")
//    public ResponseEntity<?> updateUser(){
//        return null;
//    }
//    @DeleteMapping("/users/{id}")
//    public ResponseEntity<?> deleteUser(){
//        return null;
//    }
}
