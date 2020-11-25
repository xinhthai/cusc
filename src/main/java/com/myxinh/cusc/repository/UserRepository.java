package com.myxinh.cusc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.myxinh.cusc.models.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,String> {

    @Query("select user from UserEntity user join fetch user.roles where user.userName =:userName and user.active = true")
    Optional<UserEntity> findByName(@Param("userName") String userName);// find by Name
}


