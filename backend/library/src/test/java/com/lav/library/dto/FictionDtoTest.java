/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.dto;

import com.lav.library.domain.Author;
import java.util.ArrayList;
import java.util.List;
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
public class FictionDtoTest {
    
   FictionDto fictionDto;

    @BeforeEach
    void setUp() {
        fictionDto = new FictionDto();
    }

    @AfterEach
    void tearDown() {
        fictionDto = null;
    }

    @Test
    void testFictionDto() {
        assertNotNull(fictionDto);
        assertNull(fictionDto.getBookId());
        assertNull(fictionDto.getName());
        assertEquals(0, fictionDto.getYear());
        assertFalse(fictionDto.isTaken());
        assertNull(fictionDto.getGenre());
        assertNull(fictionDto.getTheme());
        assertNull(fictionDto.getWonPrizes());
        assertNull(fictionDto.getAuthors());
    }

    @Test
    void testFictionDtoWithParameters() {
        List<Author> authors = new ArrayList<>();
        Author author = new Author();
        author.setAuthorId(1L);
        author.setFirstName("Marko");
        author.setLastName("Markovic");
        authors.add(author);
        
        FictionDto fictionDto = new FictionDto(1L, "Test Book", 2022, true, "Genre", "Theme", "Prizes", authors);
        
        assertNotNull(fictionDto);
        assertEquals(1L, fictionDto.getBookId());
        assertEquals("Test Book", fictionDto.getName());
        assertEquals(2022, fictionDto.getYear());
        assertTrue(fictionDto.isTaken());
        assertEquals("Genre", fictionDto.getGenre());
        assertEquals("Theme", fictionDto.getTheme());
        assertEquals("Prizes", fictionDto.getWonPrizes());
        assertEquals(authors, fictionDto.getAuthors());
    }

    @Test
    void testSettersAndGetters() {
        List<Author> authors = new ArrayList<>();
        Author author = new Author();
        author.setAuthorId(1L);
        author.setFirstName("Marko");
        author.setLastName("Markovic");
        authors.add(author);
        
        fictionDto.setBookId(1L);
        fictionDto.setName("Test Book");
        fictionDto.setYear(2022);
        fictionDto.setTaken(true);
        fictionDto.setGenre("Genre");
        fictionDto.setTheme("Theme");
        fictionDto.setWonPrizes("Prizes");
        fictionDto.setAuthors(authors);

        assertEquals(1L, fictionDto.getBookId());
        assertEquals("Test Book", fictionDto.getName());
        assertEquals(2022, fictionDto.getYear());
        assertTrue(fictionDto.isTaken());
        assertEquals("Genre", fictionDto.getGenre());
        assertEquals("Theme", fictionDto.getTheme());
        assertEquals("Prizes", fictionDto.getWonPrizes());
        assertEquals(authors, fictionDto.getAuthors());
    }
    
}
