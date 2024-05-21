/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.repository;

import com.lav.library.domain.Book;
import com.lav.library.domain.Fiction;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author Lav Jovanovic
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FictionRepositoryTest {
    
    @Autowired
    private FictionRepository fictionRepository;

    @Autowired
    private BookRepository bookRepository;

    private Fiction fiction;
    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book();
        book.setName("Fictional Book");
        book.setYear(2021);
        book.setTaken(false);
        
        fiction = new Fiction();
        fiction.setBook(book);
        fiction.setGenre("Science Fiction");
        fiction.setTheme("Future");
        fiction.setWonPrizes("Hugo Award");
    }

    @AfterEach
    public void tearDown() {
        fictionRepository.deleteAll();
        bookRepository.deleteAll();
        fiction = null;
        book = null;
    }

    @Test
    public void saveFictionTest() {
        Book savedBook = bookRepository.save(book);
        fiction.setBook(savedBook);
        
        Fiction savedFiction = fictionRepository.save(fiction);
        
        assertNotNull(savedFiction);
        assertEquals(fiction.getId(), savedFiction.getId());
        assertEquals(fiction.getGenre(), savedFiction.getGenre());
        assertEquals(fiction.getTheme(), savedFiction.getTheme());
        assertEquals(fiction.getWonPrizes(), savedFiction.getWonPrizes());
    }

    @Test
    public void getAllFictionsTest() {
        Book savedBook = bookRepository.save(book);
        fiction.setBook(savedBook);
        fictionRepository.save(fiction);

        List<Fiction> fictions = fictionRepository.findAll();
        assertFalse(fictions.isEmpty());
        assertEquals(1, fictions.size());
    }

    @Test
    public void findFictionByIdTest() {
        Book savedBook = bookRepository.save(book);
        fiction.setBook(savedBook);
        Fiction savedFiction = fictionRepository.save(fiction);

        Optional<Fiction> foundFiction = fictionRepository.findById(savedFiction.getId());
        assertTrue(foundFiction.isPresent());
        assertEquals(savedFiction.getId(), foundFiction.get().getId());
    }

    @Test
    public void deleteFictionTest() {
        Book savedBook = bookRepository.save(book);
        fiction.setBook(savedBook);
        Fiction savedFiction = fictionRepository.save(fiction);

        fictionRepository.delete(savedFiction);
        Optional<Fiction> deletedFiction = fictionRepository.findById(savedFiction.getId());
        assertFalse(deletedFiction.isPresent());
    }
    
}
