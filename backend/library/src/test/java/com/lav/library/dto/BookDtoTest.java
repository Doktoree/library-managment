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
 * @author Lav
 */
public class BookDtoTest {

    BookDto bookDto;

    @BeforeEach
    void setUp() {
        bookDto = new BookDto();
    }

    @AfterEach
    void tearDown() {
        bookDto = null;
    }

    @Test
    void testBookDto() {
        assertNotNull(bookDto);
        assertNull(bookDto.getBookId());
        assertNull(bookDto.getName());
        assertEquals(0, bookDto.getYear());
        assertFalse(bookDto.isTaken());
    }

    @Test
    void testBookDtoLongStringIntBoolean() {
        BookDto bookDto = new BookDto(1L, "Test Book", 2022, true);
        assertNotNull(bookDto);
        assertEquals(1L, bookDto.getBookId());
        assertEquals("Test Book", bookDto.getName());
        assertEquals(2022, bookDto.getYear());
        assertTrue(bookDto.isTaken());
    }

    @Test
    void testSetBookId() {
        bookDto.setBookId(1L);
        assertEquals(1L, bookDto.getBookId());
    }

    @Test
    void testSetName() {
        bookDto.setName("Test Book");
        assertEquals("Test Book", bookDto.getName());
    }

    @Test
    void testSetYear() {
        bookDto.setYear(2022);
        assertEquals(2022, bookDto.getYear());
    }

    @Test
    void testSetTaken() {
        bookDto.setTaken(true);
        assertTrue(bookDto.isTaken());
    }

}
