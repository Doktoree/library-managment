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
public class BookTest {
    
    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book();
    }

    @AfterEach
    public void tearDown() {
        book = null;
    }

    @Test
    public void testBook() {
        assertNotNull(book);
        assertNull(book.getBookId());
        assertNull(book.getName());
        assertEquals(0, book.getYear());
        assertFalse(book.isTaken());
    }

    @Test
    public void testBookWithParameters() {
        book = new Book(1L, "Example Book", 2022, true);
        assertNotNull(book);
        assertEquals(1L, book.getBookId());
        assertEquals("Example Book", book.getName());
        assertEquals(2022, book.getYear());
        assertTrue(book.isTaken());
    }

    @Test
    public void testSetBookId() {
        book.setBookId(1L);
        assertEquals(1L, book.getBookId());
    }

    @Test
    public void testSetName() {
        book.setName("Example Book");
        assertEquals("Example Book", book.getName());
    }

    @Test
    public void testSetYear() {
        book.setYear(2022);
        assertEquals(2022, book.getYear());
    }

    @Test
    public void testSetTaken() {
        book.setTaken(true);
        assertTrue(book.isTaken());
    }
    
}
