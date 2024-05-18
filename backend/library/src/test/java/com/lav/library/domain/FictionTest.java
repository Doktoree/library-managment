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
public class FictionTest {
    
    private Fiction fiction;

    @BeforeEach
    public void setUp() {
        fiction = new Fiction();
    }

    @AfterEach
    public void tearDown() {
        fiction = null;
    }

    @Test
    public void testFiction() {
        assertNotNull(fiction);
        assertNull(fiction.getId());
        assertNull(fiction.getBook());
        assertNull(fiction.getGenre());
        assertNull(fiction.getTheme());
        assertNull(fiction.getWonPrizes());
    }

    @Test
    public void testSetAndGetId() {
        Long id = 1L;
        fiction.setId(id);
        assertEquals(id, fiction.getId());
    }

    @Test
    public void testSetAndGetBook() {
        Book book = new Book();
        fiction.setBook(book);
        assertEquals(book, fiction.getBook());
    }

    @Test
    public void testSetAndGetGenre() {
        String genre = "Sci-Fi";
        fiction.setGenre(genre);
        assertEquals(genre, fiction.getGenre());
    }

    @Test
    public void testSetAndGetTheme() {
        String theme = "Time Travel";
        fiction.setTheme(theme);
        assertEquals(theme, fiction.getTheme());
    }

    @Test
    public void testSetAndGetWonPrizes() {
        String wonPrizes = "Hugo Award";
        fiction.setWonPrizes(wonPrizes);
        assertEquals(wonPrizes, fiction.getWonPrizes());
    }
    
}
