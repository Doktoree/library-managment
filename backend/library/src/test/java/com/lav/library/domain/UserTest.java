/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Lav
 */
public class UserTest {

    User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @AfterEach
    void tearDown() {
        user = null;
    }

    @Test
    void testUser() {
        assertNotNull(user);
        assertEquals(0, user.getUserId());
        assertNull(user.getUsername());
        assertNull(user.getPassword());
        assertNull(user.getFirstName());
        assertNull(user.getPrezime());
    }

    @Test
    void testUserLongStringStringStringString() {
        User user = new User(1L, "username", "password", "firstName", "prezime");
        assertNotNull(user);
        assertEquals(1L, user.getUserId());
        assertEquals("username", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals("firstName", user.getFirstName());
        assertEquals("prezime", user.getPrezime());
    }

    @Test
    void testSetUserId() {
        user.setUserId(1L);
        assertEquals(1L, user.getUserId());
    }

    @Test
    void testSetUsername() {
        user.setUsername("username");
        assertEquals("username", user.getUsername());
    }

    @Test
    void testSetPassword() {
        user.setPassword("password");
        assertEquals("password", user.getPassword());
    }

    @Test
    void testSetFirstName() {
        user.setFirstName("firstName");
        assertEquals("firstName", user.getFirstName());
    }

    @Test
    void testSetPrezime() {
        user.setPrezime("prezime");
        assertEquals("prezime", user.getPrezime());
    }

}
