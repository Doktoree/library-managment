/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.mapper;

import com.lav.library.domain.Author;
import com.lav.library.domain.Book;
import com.lav.library.domain.ProfessionalLiterature;
import com.lav.library.dto.BookDto;
import com.lav.library.dto.ProfessionalLiteratureDto;
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
public class ProfessionalLiteratureMapperTest {

    @Test
    public void testMapToBookDto() {
        ProfessionalLiteratureDto plDto = new ProfessionalLiteratureDto();
        plDto.setBookId(1L);
        plDto.setName("Book Name");
        plDto.setYear(2022);
        plDto.setTaken(false);
        plDto.setScientificArea("Scientific Area");

        BookDto bookDto = ProfessionalLiteratureMapper.mapToBookDto(plDto);

        assertNotNull(bookDto);
        assertEquals(plDto.getBookId(), bookDto.getBookId());
        assertEquals(plDto.getName(), bookDto.getName());
        assertEquals(plDto.getYear(), bookDto.getYear());
        assertEquals(plDto.isTaken(), bookDto.isTaken());
    }

    @Test
    public void testMapToProfessionalLiterature() {
        ProfessionalLiteratureDto plDto = new ProfessionalLiteratureDto();
        plDto.setBookId(1L);
        plDto.setName("Book Name");
        plDto.setYear(2022);
        plDto.setTaken(false);
        plDto.setScientificArea("Scientific Area");

        Book book = new Book();
        book.setBookId(1L);

        ProfessionalLiterature pl = ProfessionalLiteratureMapper.mapToProfessionalLiterature(plDto, book);

        assertNotNull(pl);
        assertEquals(plDto.getBookId(), pl.getId());
        assertEquals(book, pl.getBook());
        assertEquals(plDto.getScientificArea(), pl.getScientificArea());
    }

    @Test
    public void testMapToProfessionalLiteratureDto() {
        ProfessionalLiterature pl = new ProfessionalLiterature();
        pl.setId(1L);
        pl.setScientificArea("Scientific Area");

        Book book = new Book();
        book.setName("Book Name");
        book.setYear(2022);
        book.setTaken(false);

        List<Author> authors = new ArrayList<>();

        ProfessionalLiteratureDto plDto = ProfessionalLiteratureMapper.mapToProfessionalLiteratureDto(pl, book, authors);

        assertNotNull(plDto);
        assertEquals(pl.getId(), plDto.getBookId());
        assertEquals(book.getName(), plDto.getName());
        assertEquals(book.getYear(), plDto.getYear());
        assertEquals(book.isTaken(), plDto.isTaken());
        assertEquals(pl.getScientificArea(), plDto.getScientificArea());
        assertEquals(authors, plDto.getAuthors());
    }

}
