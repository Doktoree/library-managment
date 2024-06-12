/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.dto;

import com.lav.library.domain.Author;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Lav
 */
public class FictionDtoTest {

    FictionDto fictionDto;
    private Validator validator;

    @BeforeEach
    void setUp() {
        fictionDto = new FictionDto();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
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
        assertNotNull(fictionDto.getAuthors());
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

    @Test
    void testFictionDtoWithInvalidName() {
        fictionDto = new FictionDto(1L, null, 2022, true, "Genre", "Theme", "Prizes", new ArrayList<>());

        Set<ConstraintViolation<FictionDto>> violations = validator.validate(fictionDto);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("name") && v.getMessage().equals("Name is required!")));
    }

    @Test
    void testFictionDtoWithEmptyName() {
        fictionDto = new FictionDto(1L, "", 2022, true, "Genre", "Theme", "Prizes", new ArrayList<>());

        Set<ConstraintViolation<FictionDto>> violations = validator.validate(fictionDto);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("name") && v.getMessage().equals("Name is required!")));
    }

    @Test
    void testFictionDtoWithInvalidGenre() {
        fictionDto = new FictionDto(1L, "Test Book", 2022, true, null, "Theme", "Prizes", new ArrayList<>());

        Set<ConstraintViolation<FictionDto>> violations = validator.validate(fictionDto);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("genre") && v.getMessage().equals("Genre is required!")));
    }

    @Test
    void testFictionDtoWithEmptyGenre() {
        fictionDto = new FictionDto(1L, "Test Book", 2022, true, "", "Theme", "Prizes", new ArrayList<>());

        Set<ConstraintViolation<FictionDto>> violations = validator.validate(fictionDto);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("genre") && v.getMessage().equals("Genre is required!")));
    }

    @Test
    void testFictionDtoWithInvalidTheme() {
        fictionDto = new FictionDto(1L, "Test Book", 2022, true, "Genre", null, "Prizes", new ArrayList<>());

        Set<ConstraintViolation<FictionDto>> violations = validator.validate(fictionDto);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("theme") && v.getMessage().equals("Theme is required!")));
    }

    @Test
    void testFictionDtoWithEmptyTheme() {
        fictionDto = new FictionDto(1L, "Test Book", 2022, true, "Genre", "", "Prizes", new ArrayList<>());

        Set<ConstraintViolation<FictionDto>> violations = validator.validate(fictionDto);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("theme") && v.getMessage().equals("Theme is required!")));
    }

    @Test
    void testFictionDtoWithInvalidWonPrizes() {
        fictionDto = new FictionDto(1L, "Test Book", 2022, true, "Genre", "Theme", null, new ArrayList<>());

        Set<ConstraintViolation<FictionDto>> violations = validator.validate(fictionDto);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("wonPrizes") && v.getMessage().equals("Won prizes is required!")));
    }

    @Test
    void testFictionDtoWithEmptyWonPrizes() {
        fictionDto = new FictionDto(1L, "Test Book", 2022, true, "Genre", "Theme", "", new ArrayList<>());

        Set<ConstraintViolation<FictionDto>> violations = validator.validate(fictionDto);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("wonPrizes") && v.getMessage().equals("Won prizes is required!")));
    }
    
    @Test
    public void testYearValidCurrentYear() {
        int currentYear = LocalDate.now().getYear();
        fictionDto.setYear(currentYear);
        assertTrue(fictionDto.isYearValid(), "Year must be a valid number");
    }

    @Test
    public void testYearValidPastYear() {
        fictionDto.setYear(1990);
        assertTrue(fictionDto.isYearValid(), "Year must be a valid number");
    }

    @Test
    public void testYearInvalidFutureYear() {
        int futureYear = LocalDate.now().getYear() + 1;
        fictionDto.setYear(futureYear);
        assertFalse(fictionDto.isYearValid(), "Year must be a valid number");
    }
}
