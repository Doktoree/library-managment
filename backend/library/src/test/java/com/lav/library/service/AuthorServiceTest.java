/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.service;

import com.lav.library.domain.Author;
import com.lav.library.domain.Book;
import com.lav.library.domain.BookAuthor;
import com.lav.library.ids.BookAuthorId;
import com.lav.library.repository.AuthorRepository;
import com.lav.library.repository.BookAuthorRepository;
import com.lav.library.repository.BookRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author Lav Jovanovic
 */

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookAuthorRepository bookAuthorRepository;

    @InjectMocks
    private AuthorService authorService;

    private Author author;

    @BeforeEach
    public void setup() {
        author = new Author();
        author.setAuthorId(1L);
        author.setFirstName("Lav");
        author.setLastName("Jovanovic");
    }

    @Test
    public void getAuthorsByBookIdTest() {
        Book book = new Book();
        book.setBookId(1L);
        book.setName("Test Book");
        book.setYear(2023);
        book.setTaken(false);

        BookAuthor bookAuthor = new BookAuthor();
        BookAuthorId bookAuthorId = new BookAuthorId(book, author);
        bookAuthor.setId(bookAuthorId);

        List<BookAuthor> bookAuthors = new ArrayList<>();
        bookAuthors.add(bookAuthor);

        given(bookAuthorRepository.findById_BookBookId(1L)).willReturn(bookAuthors);
        given(authorRepository.findById(author.getAuthorId())).willReturn(Optional.of(author));

        
        List<Author> authors = authorService.getAuthorsByBookId(1L);

        
        assertThat(authors).isNotNull();
        assertThat(authors.size()).isEqualTo(1);
        assertThat(authors.get(0).getAuthorId()).isEqualTo(author.getAuthorId());
        assertThat(authors.get(0).getFirstName()).isEqualTo(author.getFirstName());
        assertThat(authors.get(0).getLastName()).isEqualTo(author.getLastName());
    }

}
