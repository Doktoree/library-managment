/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.repository;

import com.lav.library.domain.Author;
import com.lav.library.domain.Book;
import com.lav.library.domain.BookAuthor;
import com.lav.library.ids.BookAuthorId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
public class BookAuthorRepositoryTest {
    
    
    @Autowired
    private BookAuthorRepository bookAuthorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    
    private BookAuthor bookAuthor;
    private Book book;
    private Author author1;
    
    @BeforeEach
    public void setUp() {
        
        bookAuthor = new BookAuthor();
        
        book = new Book();
        book.setName("Book 1");
        book.setTaken(false);
        book.setYear(1999);
        
        author1 = new Author();
        author1.setFirstName("Mirko");
        author1.setLastName("Mirkovic");
        
    }
    
    @AfterEach
    public void tearDown() {
        
        bookAuthorRepository.deleteAll();
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        bookAuthor = null;
        book = null;
        author1 = null;
        
    }

    
    @Test
    public void saveBookAuthorTest(){
        
        Book savedBook = bookRepository.save(book);
        Author savedAuthor1 = authorRepository.save(author1);
        
        BookAuthorId bookAuthorId1 = new BookAuthorId(book, author1);
        bookAuthor.setId(bookAuthorId1);
        BookAuthor savedBookAuthor = bookAuthorRepository.save(bookAuthor);

        assertEquals(savedBookAuthor.getId().getBook().getBookId(), savedBook.getBookId());
        assertEquals(savedBookAuthor.getId().getAuthor().getAuthorId(), savedAuthor1.getAuthorId());
    }
 
    @Test
    public void getAllBookAuthorsTest() {
        Book savedBook = bookRepository.save(book);
        Author savedAuthor1 = authorRepository.save(author1);

        BookAuthorId bookAuthorId1 = new BookAuthorId(savedBook, savedAuthor1);
        bookAuthor.setId(bookAuthorId1);
        bookAuthorRepository.save(bookAuthor);

        Iterable<BookAuthor> bookAuthors = bookAuthorRepository.findAll();
        List<BookAuthor> bookAuthorList = new ArrayList<>();
        bookAuthors.forEach(bookAuthorList::add);

        assertFalse(bookAuthorList.isEmpty());
        assertEquals(1, bookAuthorList.size());
    }

    @Test
    public void findBookAuthorByIdTest() {
        Book savedBook = bookRepository.save(book);
        Author savedAuthor1 = authorRepository.save(author1);

        BookAuthorId bookAuthorId1 = new BookAuthorId(savedBook, savedAuthor1);
        bookAuthor.setId(bookAuthorId1);
        bookAuthorRepository.save(bookAuthor);

        Optional<BookAuthor> foundBookAuthor = bookAuthorRepository.findById(bookAuthorId1);
        assertTrue(foundBookAuthor.isPresent());
        assertEquals(foundBookAuthor.get().getId(), bookAuthorId1);
    }

    @Test
    public void deleteBookAuthorTest() {
        Book savedBook = bookRepository.save(book);
        Author savedAuthor1 = authorRepository.save(author1);

        BookAuthorId bookAuthorId1 = new BookAuthorId(savedBook, savedAuthor1);
        bookAuthor.setId(bookAuthorId1);
        bookAuthorRepository.save(bookAuthor);

        bookAuthorRepository.delete(bookAuthor);

        Optional<BookAuthor> deletedBookAuthor = bookAuthorRepository.findById(bookAuthorId1);
        assertFalse(deletedBookAuthor.isPresent());
    }
    
}
