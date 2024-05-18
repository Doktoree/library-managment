/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.domain;

import com.lav.library.ids.BookAuthorId;
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
public class BookAuthorTest {
    
    private BookAuthor bookAuthor;

    @BeforeEach
    public void setUp() {
        bookAuthor = new BookAuthor();
    }

    @AfterEach
    public void tearDown() {
        bookAuthor = null;
    }

    @Test
    public void testBookAuthor() {
        assertNotNull(bookAuthor);
        assertNull(bookAuthor.getId());
    }

    @Test
    public void testSetAndGetId() {
        BookAuthorId bookAuthorId = new BookAuthorId();
        bookAuthor.setId(bookAuthorId);
        assertEquals(bookAuthorId, bookAuthor.getId());
    }
    
}
