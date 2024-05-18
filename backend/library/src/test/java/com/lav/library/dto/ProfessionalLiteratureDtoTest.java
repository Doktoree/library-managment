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
public class ProfessionalLiteratureDtoTest {

    private ProfessionalLiteratureDto professionalLiteratureDto;

    @Test
    public void testNoArgsConstructor() {
        professionalLiteratureDto = new ProfessionalLiteratureDto();
        assertNotNull(professionalLiteratureDto);
    }

    @Test
    public void testAllArgsConstructor() {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author());
        professionalLiteratureDto = new ProfessionalLiteratureDto(1L, "Book Name", 2022, true, "Scientific Area", authors);

        assertEquals(1L, professionalLiteratureDto.getBookId());
        assertEquals("Book Name", professionalLiteratureDto.getName());
        assertEquals(2022, professionalLiteratureDto.getYear());
        assertTrue(professionalLiteratureDto.isTaken());
        assertEquals("Scientific Area", professionalLiteratureDto.getScientificArea());
        assertEquals(authors, professionalLiteratureDto.getAuthors());
    }

    @Test
    public void testSetBookId() {
        professionalLiteratureDto = new ProfessionalLiteratureDto();
        professionalLiteratureDto.setBookId(1L);
        assertEquals(1L, professionalLiteratureDto.getBookId());
    }

    @Test
    public void testSetName() {
        professionalLiteratureDto = new ProfessionalLiteratureDto();
        professionalLiteratureDto.setName("Book Name");
        assertEquals("Book Name", professionalLiteratureDto.getName());
    }

    @Test
    public void testSetYear() {
        professionalLiteratureDto = new ProfessionalLiteratureDto();
        professionalLiteratureDto.setYear(2022);
        assertEquals(2022, professionalLiteratureDto.getYear());
    }

    @Test
    public void testSetIsTaken() {
        professionalLiteratureDto = new ProfessionalLiteratureDto();
        professionalLiteratureDto.setTaken(true);
        assertTrue(professionalLiteratureDto.isTaken());
    }

    @Test
    public void testSetScientificArea() {
        professionalLiteratureDto = new ProfessionalLiteratureDto();
        professionalLiteratureDto.setScientificArea("Scientific Area");
        assertEquals("Scientific Area", professionalLiteratureDto.getScientificArea());
    }

    @Test
    public void testSetAuthors() {
        professionalLiteratureDto = new ProfessionalLiteratureDto();
        List<Author> authors = new ArrayList<>();
        authors.add(new Author());
        professionalLiteratureDto.setAuthors(authors);
        assertEquals(authors, professionalLiteratureDto.getAuthors());
    }

}
