package com.myxinh.cusc.service;

import com.myxinh.cusc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService{

    @Autowired
    UserRepository userRepository;

//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        Optional<UserEntity> result = userRepository.findByName(userName);
//        if(result.isEmpty()) {
//            throw new UsernameNotFoundException("User not found");
//        }
//        UserEntity user = result.get();
//        // get All Roles of User then for each role -->add to SimpleGrantedAuthority
//        List<GrantedAuthority> authorities = user.getRoles()
//                .stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toList());
//        //convert UserEntity -->UserDetails because Spring Security only know UserDetails
//        return new User(user.getUserName(),user.getPassword(),authorities);
//    }



}
