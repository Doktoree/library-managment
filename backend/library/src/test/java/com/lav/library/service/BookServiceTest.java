/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.service;

import com.lav.library.domain.Author;
import com.lav.library.domain.Book;
import com.lav.library.domain.BookAuthor;
import com.lav.library.domain.ProfessionalLiterature;
import com.lav.library.dto.BookDto;
import com.lav.library.dto.ProfessionalLiteratureDto;
import com.lav.library.ids.BookAuthorId;
import com.lav.library.repository.AuthorRepository;
import com.lav.library.repository.BookAuthorRepository;
import com.lav.library.repository.BookRepository;
import com.lav.library.repository.FictionRepository;
import com.lav.library.repository.ProfessionalLiteratureRepository;
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
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author Lav Jovanovic
 */

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    
    @Mock
    private BookRepository bookRepository;

    @Mock
    private ProfessionalLiteratureRepository plRepo;

    @Mock
    private FictionRepository fictionRepository;

    @Mock
    private BookAuthorRepository bookAuthorRepository;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private BookService bookService;

    private BookDto bookDto;

    @BeforeEach
    public void setup() {
        bookDto = new BookDto();
        bookDto.setName("Test Book");
        bookDto.setYear(2023);
    }

    @Test
    public void getAllBooksTest() {
        List<Book> allBooks = new ArrayList<>();
        Book book = new Book();
        book.setBookId(1L);
        book.setName("Test Book");
        book.setYear(2023);
        allBooks.add(book);

        Book book2 = new Book(2L, "Book 2", 2000, true);
        allBooks.add(book2);
        
        given(bookRepository.findAll()).willReturn(allBooks);

        List<BookDto> bookDtoList = bookService.getAllBooks();

        assertThat(bookDtoList).isNotNull();
        assertThat(bookDtoList.size()).isEqualTo(2);
        
    }

    @Test
    public void createBookTest() {
        Book book = new Book();
        book.setBookId(1L);
        book.setName("Test Book");
        book.setYear(2023);
        given(bookRepository.save(any(Book.class))).willReturn(book);

        BookDto createdBookDto = bookService.createBook(bookDto);

        assertThat(createdBookDto).isNotNull();
        assertThat(createdBookDto.getName()).isEqualTo(bookDto.getName());
        assertThat(createdBookDto.getYear()).isEqualTo(bookDto.getYear());
    }
    
    @Test
    public void getBookTest() {
        Book book = new Book();
        book.setBookId(1L);
        book.setName("Test Book");
        book.setYear(2023);
        ProfessionalLiterature pl = new ProfessionalLiterature();
        pl.setId(1L);
        pl.setBook(book);
        pl.setScientificArea("Science");
        
        given(bookRepository.findById(1L)).willReturn(Optional.of(book));
        given(plRepo.findById(1L)).willReturn(Optional.of(pl));

        Object result = bookService.getBook(1L, new ArrayList<>());

        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(ProfessionalLiteratureDto.class);
    }

    @Test
    public void getBooksTest() {
        Book book = new Book();
        book.setBookId(1L);
        book.setName("Test Book");
        book.setYear(2023);
        List<Book> books = new ArrayList<>();
        books.add(book);

        ProfessionalLiterature pl = new ProfessionalLiterature();
        pl.setId(1L);
        pl.setBook(book);
        pl.setScientificArea("Science");
        
        given(bookRepository.findByYearAndName(2023, "Test Book")).willReturn(books);
        given(plRepo.findById(1L)).willReturn(Optional.of(pl));

        List<Object> result = bookService.getBooks(bookDto);

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0)).isInstanceOf(ProfessionalLiteratureDto.class);
    }

    @Test
    public void deleteBookTest() {
        Book book = new Book();
        book.setBookId(1L);
        book.setName("Test Book");
        book.setYear(2023);
        book.setTaken(false);
        
        given(bookRepository.findById(1L)).willReturn(Optional.of(book));

        boolean result = bookService.deleteBook(1L);

        assertThat(result).isTrue();
    }

    @Test
    public void getAuthorsByBookIdTest() {
        Book book = new Book();
        book.setBookId(1L);
        
        Author author = new Author();
        author.setAuthorId(1L);
        author.setFirstName("John");
        author.setLastName("Doe");

        BookAuthor bookAuthor = new BookAuthor();
        BookAuthorId bookAuthorId = new BookAuthorId(book, author);
        bookAuthor.setId(bookAuthorId);
        List<BookAuthor> bookAuthors = new ArrayList<>();
        bookAuthors.add(bookAuthor);

        given(bookAuthorRepository.findById_BookBookId(1L)).willReturn(bookAuthors);
        given(authorRepository.findById(1L)).willReturn(Optional.of(author));

        List<Author> authors = bookService.getAuthorsByBookId(1L);

        assertThat(authors).isNotNull();
        assertThat(authors.size()).isEqualTo(1);
        assertThat(authors.get(0).getFirstName()).isEqualTo("John");
    }
}
