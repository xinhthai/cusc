package com.myxinh.cusc.repository;

import com.myxinh.cusc.service.dto.UserDTO;
import com.myxinh.cusc.service.dto.ui.NewsViewDTO;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import com.myxinh.cusc.domain.UserEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,String>{

    @EntityGraph(attributePaths = "roles")
    Optional<UserEntity> findOneWithAuthoritiesByUsername(String userName);

//    @Query("SELECT u FROM UserEntity u WHERE u.username =:username AND u.password =:password")
//    UserEntity findUserByUsernameAndPassword(@Param("username") String username,@Param("password")String password);
}


