package com.myxinh.cusc.service;

import com.myxinh.cusc.domain.UserEntity;
import com.myxinh.cusc.repository.UserRepository;
import com.myxinh.cusc.security.UserNotActivatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username)  {
        return userRepository.findOneWithAuthoritiesByUsername(username)
                .map(userEntity -> createSpringSecurityUser(username,userEntity))
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " was not found in the database"));
    }

    private User createSpringSecurityUser(String username, UserEntity user) {
        if (!user.isActive()) {
            throw new UserNotActivatedException("User " + username + " was not activated");
        }
        List<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }


}
