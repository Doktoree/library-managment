/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.repository;

import com.lav.library.domain.Book;
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
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    public void setUp() {
        book1 = new Book();
        book1.setName("Book 1");
        book1.setYear(1999);
        book1.setTaken(false);

        book2 = new Book();
        book2.setName("Book 2");
        book2.setYear(2000);
        book2.setTaken(false);

        book3 = new Book();
        book3.setName("Book 1");
        book3.setYear(2001);
        book3.setTaken(false);

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
    }

    @AfterEach
    public void tearDown() {
        bookRepository.deleteAll();
        book1 = null;
        book2 = null;
        book3 = null;
    }

    @Test
    public void saveBookTest() {
        Book savedBook = bookRepository.save(book1);
        assertNotNull(savedBook);
        assertEquals(book1.getBookId(), savedBook.getBookId());
    }

    @Test
    public void getAllBooksTest() {
        List<Book> books = bookRepository.findAll();
        assertFalse(books.isEmpty());
        assertEquals(3, books.size());
    }

    @Test
    public void findBookByIdTest() {
        Optional<Book> foundBook = bookRepository.findById(book1.getBookId());
        assertTrue(foundBook.isPresent());
        assertEquals(book1.getBookId(), foundBook.get().getBookId());
    }

    @Test
    public void deleteBookTest() {
        bookRepository.delete(book1);
        Optional<Book> deletedBook = bookRepository.findById(book1.getBookId());
        assertFalse(deletedBook.isPresent());
    }

    @Test
    public void findBooksByNameTest() {
        List<Book> books = bookRepository.findByName("Book 1");
        assertFalse(books.isEmpty());
        assertEquals(2, books.size());
        for (Book book : books) {
            assertEquals("Book 1", book.getName());
        }
    }

    @Test
    public void findBooksByYearTest() {
        List<Book> books = bookRepository.findByYear(2000);
        assertFalse(books.isEmpty());
        assertEquals(1, books.size());
        assertEquals(2000, books.get(0).getYear());
    }

    @Test
    public void findBooksByYearAndNameTest() {
        List<Book> books = bookRepository.findByYearAndName(2001, "Book 1");
        assertFalse(books.isEmpty());
        assertEquals(1, books.size());
        Book foundBook = books.get(0);
        assertEquals(2001, foundBook.getYear());
        assertEquals("Book 1", foundBook.getName());
    }

}
