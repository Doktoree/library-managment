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
public class AuthorTest {

    private Author author;

    @BeforeEach
    public void setUp() {
        author = new Author();
    }

    @AfterEach
    public void tearDown() {
        author = null;
    }

    @Test
    public void testAuthor() {

        assertNotNull(author);
        assertNull(author.getFirstName());
        assertNull(author.getLastName());
        assertNull(author.getAuthorId());

    }

    @Test
    public void testAuthorLongStringString() {

        author = new Author(1L, "Marko", "Marković");
        assertNotNull(author);
        assertEquals("Marko", author.getFirstName());
        assertEquals("Marković", author.getLastName());
        assertEquals(1L, author.getAuthorId());
    }

    @Test
    public void testSetAuthorId() {
        author.setAuthorId(1L);
        assertEquals(1L, author.getAuthorId());
    }


    @Test
    public void testSetFirstName() {
        author.setFirstName("Marko");
        assertEquals("Marko", author.getFirstName());
    }


    @Test
    public void testSetLastName() {
        author.setLastName("Marko");
        assertEquals("Marko", author.getLastName());
    }
}
