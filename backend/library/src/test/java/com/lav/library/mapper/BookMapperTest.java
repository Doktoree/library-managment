/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.mapper;

import com.lav.library.domain.Book;
import com.lav.library.dto.BookDto;
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
public class BookMapperTest {

    @Test
    public void testMapToBookDto() {
        Book book = new Book();
        book.setBookId(1L);
        book.setName("Effective Java");
        book.setYear(2018);
        book.setTaken(true);

        BookDto bookDto = BookMapper.mapToBookDto(book);

        assertNotNull(bookDto);
        assertEquals(book.getBookId(), bookDto.getBookId());
        assertEquals(book.getName(), bookDto.getName());
        assertEquals(book.getYear(), bookDto.getYear());
        assertTrue(bookDto.isTaken());
    }

    @Test
    public void testMapToBook() {

        BookDto bookDto = new BookDto();
        bookDto.setBookId(1L);
        bookDto.setName("Effective Java");
        bookDto.setYear(2018);
        bookDto.setTaken(true);

        Book book = BookMapper.mapToBook(bookDto);

        assertNotNull(book);
        assertEquals(bookDto.getBookId(), book.getBookId());
        assertEquals(bookDto.getName(), book.getName());
        assertEquals(bookDto.getYear(), book.getYear());
        assertTrue(book.isTaken());
    }

}
