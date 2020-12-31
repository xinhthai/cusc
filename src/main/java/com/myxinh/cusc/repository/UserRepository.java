package com.myxinh.cusc.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import com.myxinh.cusc.domain.UserEntity;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,String>{

    @EntityGraph(attributePaths = "roles")
    Optional<UserEntity> findOneWithAuthoritiesByUsername(String userName);


}


