/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.repository;

import com.lav.library.domain.User;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author Lav
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    
    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUsername("johndoe");
        user.setPassword("password123");
        user.setFirstName("John");
        user.setPrezime("Doe");
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
        user = null;
    }

    @Test
    public void saveUserTest() {
        User savedUser = userRepository.save(user);
        assertNotNull(savedUser);
        assertEquals(user.getUsername(), savedUser.getUsername());
        assertEquals(user.getPassword(), savedUser.getPassword());
        assertEquals(user.getFirstName(), savedUser.getFirstName());
        assertEquals(user.getPrezime(), savedUser.getPrezime());
    }

    @Test
    public void getAllUsersTest() {
        userRepository.save(user);
        List<User> users = userRepository.findAll();
        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
    }

    @Test
    public void findUserByIdTest() {
        User savedUser = userRepository.save(user);
        Optional<User> foundUser = userRepository.findById(savedUser.getUserId());
        assertTrue(foundUser.isPresent());
        assertEquals(savedUser.getUserId(), foundUser.get().getUserId());
    }

    @Test
    public void deleteUserTest() {
        User savedUser = userRepository.save(user);
        userRepository.delete(savedUser);
        Optional<User> deletedUser = userRepository.findById(savedUser.getUserId());
        assertFalse(deletedUser.isPresent());
    }
    
}
