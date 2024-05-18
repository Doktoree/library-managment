/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.dto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Lav Jovanovic
 */
public class AuthorDtoTest {

    AuthorDto authorDto;

    @BeforeEach
    void setUp() {
        authorDto = new AuthorDto();
    }

    @AfterEach
    void tearDown() {
        authorDto = null;
    }

    @Test
    void testAuthorDto() {
        assertNotNull(authorDto);
        assertNull(authorDto.getAuthorId());
        assertNull(authorDto.getFirstName());
        assertNull(authorDto.getLastName());
    }

    @Test
    void testAuthorDtoLongStringString() {
        AuthorDto authorDto = new AuthorDto(1L, "Marko", "Marković");
        assertNotNull(authorDto);
        assertEquals(1L, authorDto.getAuthorId());
        assertEquals("Marko", authorDto.getFirstName());
        assertEquals("Marković", authorDto.getLastName());
    }

    @Test
    void testSetAuthorId() {
        authorDto.setAuthorId(1L);
        assertEquals(1L, authorDto.getAuthorId());
    }

    @Test
    void testSetFirstName() {
        authorDto.setFirstName("Marko");
        assertEquals("Marko", authorDto.getFirstName());
    }

    @Test
    void testSetLastName() {
        authorDto.setLastName("Marković");
        assertEquals("Marković", authorDto.getLastName());
    }

}
