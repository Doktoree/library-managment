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
public class ProfessionalLiteratureTest {

    ProfessionalLiterature pl;

    @BeforeEach
    void setUp() throws Exception {
        pl = new ProfessionalLiterature();
    }

    @AfterEach
    void tearDown() throws Exception {
        pl = null;
    }

    @Test
    void testProfessionalLiterature() {
        assertNotNull(pl);
        assertNull(pl.getId());
        assertNull(pl.getBook());
        assertNull(pl.getScientificArea());
    }

    @Test
    void testProfessionalLiteratureLongBookString() {
        Book book = new Book();
        pl = new ProfessionalLiterature(1L, book, "Scientific Area");

        assertNotNull(pl);
        assertEquals(1L, pl.getId());
        assertEquals(book, pl.getBook());
        assertEquals("Scientific Area", pl.getScientificArea());
    }

    @Test
    void testSetId() {
        pl.setId(1L);
        assertEquals(1L, pl.getId());
    }

    @Test
    void testSetBook() {
        Book book = new Book();
        pl.setBook(book);
        assertEquals(book, pl.getBook());
    }

    @Test
    void testSetScientificArea() {
        pl.setScientificArea("Scientific Area");
        assertEquals("Scientific Area", pl.getScientificArea());
    }
}
