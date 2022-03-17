package com.example.springbootstarterthymeleaf.database;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findUserByUsername(String username);

    User findUserByUserId(Integer userId);

    Boolean existsUserByUsername(String username);
    

}
