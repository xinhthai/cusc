package com.myxinh.cusc.service;

import com.myxinh.cusc.repository.UserRepository;
import com.myxinh.cusc.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService{

    @Autowired
    UserRepository userRepository;

}
