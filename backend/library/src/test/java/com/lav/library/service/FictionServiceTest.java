/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.service;

import com.lav.library.domain.Author;
import com.lav.library.domain.Book;
import com.lav.library.domain.Fiction;
import com.lav.library.dto.FictionDto;
import com.lav.library.repository.AuthorRepository;
import com.lav.library.repository.BookAuthorRepository;
import com.lav.library.repository.BookRepository;
import com.lav.library.repository.FictionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author Lav Jovanovic
 */
@ExtendWith(MockitoExtension.class)
public class FictionServiceTest {
    
    @Mock
    private FictionRepository fictionRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookAuthorRepository bookAuthorRepository;

    @InjectMocks
    private FictionService fictionService;

    private FictionDto fictionDto;
    private Book book;
    private Fiction fiction;
    private Author author;

    @BeforeEach
    public void setup() {
        fictionDto = new FictionDto();
        fictionDto.setName("Test Fiction");
        fictionDto.setGenre("Fantasy");
        fictionDto.setTheme("Adventure");
        fictionDto.setWonPrizes("none");
        fictionDto.setYear(2023);
        fictionDto.setTaken(false);
        author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");
        List<Author> authors = new ArrayList<>();
        authors.add(author);
        fictionDto.setAuthors(authors);

        book = new Book();
        book.setBookId(1L);
        book.setName("Test Fiction");
        book.setYear(2023);
        book.setTaken(false);

        fiction = new Fiction();
        fiction.setId(1L);
        fiction.setGenre("Fantasy");
        fiction.setTheme("Adventure");
        fiction.setWonPrizes("none");
        fiction.setBook(book);
    }

    @Test
    public void createFictionTest() {
        given(bookRepository.save(any(Book.class))).willReturn(book);
        given(fictionRepository.save(any(Fiction.class))).willReturn(fiction);
        given(authorRepository.save(any(Author.class))).willReturn(author);

        FictionDto createdFictionDto = fictionService.createFiction(fictionDto);

        assertThat(createdFictionDto).isNotNull();
        assertThat(createdFictionDto.getName()).isEqualTo(fictionDto.getName());
        assertThat(createdFictionDto.getGenre()).isEqualTo(fictionDto.getGenre());
    }

    @Test
    public void saveFictionTest() {
        given(fictionRepository.findById(1L)).willReturn(Optional.of(fiction));
        given(bookRepository.findById(1L)).willReturn(Optional.of(book));
        given(fictionRepository.save(any(Fiction.class))).willReturn(fiction);
        given(bookRepository.save(any(Book.class))).willReturn(book);
        given(authorRepository.save(any(Author.class))).willReturn(author);

        FictionDto updatedFictionDto = fictionService.saveFiction(1L, fictionDto);

     
        assertThat(updatedFictionDto).isNotNull();
        assertThat(updatedFictionDto.getName()).isEqualTo(fictionDto.getName());
        assertThat(updatedFictionDto.getGenre()).isEqualTo(fictionDto.getGenre());

    
        verify(bookRepository, times(1)).save(any(Book.class));
        verify(fictionRepository, times(1)).save(any(Fiction.class));
    }
    
}
