package com.example.springbootstarterthymeleaf.database;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "SELECT * FROM User u WHERE u.username = :username",
    countQuery = "SELECT count(*) FROM User",
    nativeQuery = true)
    User findByUsername(
        @Param("username") String username
    );

    @Query(value = "SELECT * FROM User u WHERE u.userId = :userId",
    countQuery = "SELECT count(*) FROM User",
    nativeQuery = true)
    User findByUserId(
            @Param("userId") Integer userId
    );
}
