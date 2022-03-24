package com.example.springbootstarterthymeleaf;
import com.example.springbootstarterthymeleaf.database.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ApiTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    void addUserTest() {
        User user = userRepository.findUserByUsername("__test_user__");
        if (user != null) {
            userRepository.delete(user);
        }

        User newUser = new User();
        String username = "__test_user__", password = "test", firstName = "test", lastName = "user";
        int userid = 112211;
        newUser.setUsername(username);
        newUser.setUserId(userid);
        newUser.setPassword(password);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);

        userRepository.save(newUser);

        User testUser = userRepository.findUserByUsername(username);
        assert (testUser.getUserId() == userid);
        assert (testUser.getUsername().equals(username));
        assert (testUser.getPassword().equals(password));
        assert (testUser.getFirstName().equals(firstName));
        assert (testUser.getLastName().equals(lastName));
    }


    @Test
    void testUserTest() {
        User user = userRepository.findUserByUsername("__test_user__");
        if (user != null) {
            userRepository.delete(user);
        }

        User newUser = new User();
        String username = "__test_user__", password = "test", firstName = "test", lastName = "user";
        int userid = 112211;

        newUser.setUsername(username);
        newUser.setUserId(userid);
        newUser.setPassword(password);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);

        userRepository.save(newUser);

        User testUser = userRepository.findUserByUsername(username);

        assert (testUser.getUserId() == userid);
        assert (testUser.getUsername().equals(username));
        assert (testUser.getPassword().equals(password));
        assert (testUser.getFirstName().equals(firstName));
        assert (testUser.getLastName().equals(lastName));
    }


    @Test
    void UserAddedTest() {
        User user = userRepository.findUserByUsername("__test_user__");
        if (user != null) {
            userRepository.delete(user);
        }

        User newUser = new User();
        String username = "__test_user__", password = "test", firstName = "test", lastName = "user";
        int userid = 112211;
        newUser.setUsername(username);
        newUser.setUserId(userid);
        newUser.setPassword(password);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);

        userRepository.save(newUser);

        User testUser = userRepository.findUserByUsername(username);
        assert (testUser.getUserId() == userid);
        assert (testUser.getUsername().equals(username));
        assert (testUser.getPassword().equals(password));
        assert (testUser.getFirstName().equals(firstName));
        assert (testUser.getLastName().equals(lastName));
    }
}
