/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.mapper;

import com.lav.library.domain.Author;
import com.lav.library.domain.Book;
import com.lav.library.domain.Fiction;
import com.lav.library.dto.BookDto;
import com.lav.library.dto.FictionDto;
import java.util.ArrayList;
import java.util.Collections;
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
public class FictionMapperTest {

    @Test
    public void testMapToBookDto() {
        FictionDto fictionDto = new FictionDto(
                1L, "1984", 1949, true, "Dystopian", "Totalitarianism", "Various Awards", Collections.emptyList());

        BookDto bookDto = FictionMapper.mapToBookDto(fictionDto);

        assertNotNull(bookDto);
        assertEquals(fictionDto.getBookId(), bookDto.getBookId());
        assertEquals(fictionDto.getName(), bookDto.getName());
        assertEquals(fictionDto.getYear(), bookDto.getYear());
        assertTrue(bookDto.isTaken());
    }

    @Test
    public void testMapToFiction() {
        FictionDto fictionDto = new FictionDto(
                1L, "1984", 1949, true, "Dystopian", "Totalitarianism", "Various Awards", Collections.emptyList());

        Book book = new Book(
                1L, "1984", 1949, true);

        Fiction fiction = FictionMapper.mapToFiction(fictionDto, book);

        assertNotNull(fiction);
        assertEquals(fictionDto.getBookId(), fiction.getId());
        assertEquals(book, fiction.getBook());
        assertEquals(fictionDto.getGenre(), fiction.getGenre());
        assertEquals(fictionDto.getTheme(), fiction.getTheme());
        assertEquals(fictionDto.getWonPrizes(), fiction.getWonPrizes());
    }

    @Test
    public void testMapToFictionDto() {
        Fiction fiction = new Fiction(
                1L, new Book(1L, "1984", 1949, true), "Dystopian", "Totalitarianism", "Various Awards");

        Book book = fiction.getBook();

        List<Author> authors = new ArrayList<>();

        FictionDto fictionDto = FictionMapper.mapToFictionDto(fiction, book, authors);

        assertNotNull(fictionDto);
        assertEquals(fiction.getId(), fictionDto.getBookId());
        assertEquals(book.getName(), fictionDto.getName());
        assertEquals(book.getYear(), fictionDto.getYear());
        assertEquals(book.isTaken(), fictionDto.isTaken());
        assertEquals(fiction.getGenre(), fictionDto.getGenre());
        assertEquals(fiction.getTheme(), fictionDto.getTheme());
        assertEquals(fiction.getWonPrizes(), fictionDto.getWonPrizes());
        assertEquals(authors, fictionDto.getAuthors());
    }
}
