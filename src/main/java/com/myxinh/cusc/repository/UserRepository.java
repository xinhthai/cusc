package com.myxinh.cusc.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import com.myxinh.cusc.models.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,String>{
    String USERS_BY_LOGIN_CACHE = "usersByLogin";


//    @Query("select user from UserEntity user join fetch user.roles where user.userName =:userName and user.active = true")
//    Optional<UserEntity>findByName(@Param("userName") String userName);// find by Name

    @EntityGraph(attributePaths = "roles")
    Optional<UserEntity> findOneWithAuthoritiesByuserName(String userName);


}


