/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.service;

import com.lav.library.domain.Author;
import com.lav.library.domain.Book;
import com.lav.library.domain.ProfessionalLiterature;
import com.lav.library.dto.ProfessionalLiteratureDto;
import com.lav.library.repository.AuthorRepository;
import com.lav.library.repository.BookAuthorRepository;
import com.lav.library.repository.BookRepository;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author Lav Jovanovic
 */
@ExtendWith(MockitoExtension.class)
public class ProfessionalLiteratureServiceTest {
    
    @Mock
    private ProfessionalLiteratureRepository plRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookAuthorRepository bookAuthorRepository;

    @InjectMocks
    private ProfessionalLiteratureService professionalLiteratureService;

    private ProfessionalLiteratureDto professionalLiteratureDto;
    private Book book;
    private ProfessionalLiterature professionalLiterature;
    private Author author;

    @BeforeEach
    public void setup() {
        professionalLiteratureDto = new ProfessionalLiteratureDto();
        professionalLiteratureDto.setName("Test Professional Literature");
        professionalLiteratureDto.setYear(2023);
        professionalLiteratureDto.setTaken(false);
        professionalLiteratureDto.setScientificArea("Computer Science");
        author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");
        List<Author> authors = new ArrayList<>();
        authors.add(author);
        professionalLiteratureDto.setAuthors(authors);

        book = new Book();
        book.setBookId(1L);
        book.setName("Test Professional Literature");
        book.setYear(2023);
        book.setTaken(false);

        professionalLiterature = new ProfessionalLiterature();
        professionalLiterature.setId(1L);
        professionalLiterature.setScientificArea("Computer Science");
        professionalLiterature.setBook(book);
    }

    @Test
    public void createProfessionalLiteratureTest() {
        given(bookRepository.save(any(Book.class))).willReturn(book);
        given(plRepository.save(any(ProfessionalLiterature.class))).willReturn(professionalLiterature);
        given(authorRepository.save(any(Author.class))).willReturn(author);

        ProfessionalLiteratureDto createdProfessionalLiteratureDto = professionalLiteratureService.createProfessionalLiterature(professionalLiteratureDto);

        assertThat(createdProfessionalLiteratureDto).isNotNull();
        assertThat(createdProfessionalLiteratureDto.getName()).isEqualTo(professionalLiteratureDto.getName());
        assertThat(createdProfessionalLiteratureDto.getScientificArea()).isEqualTo(professionalLiteratureDto.getScientificArea());
    }

    @Test
    public void saveProfessionalLiteratureTest() {
        given(plRepository.findById(1L)).willReturn(Optional.of(professionalLiterature));
        given(bookRepository.findById(1L)).willReturn(Optional.of(book));
        given(plRepository.save(any(ProfessionalLiterature.class))).willReturn(professionalLiterature);
        given(bookRepository.save(any(Book.class))).willReturn(book);
        given(authorRepository.save(any(Author.class))).willReturn(author);

        ProfessionalLiteratureDto updatedProfessionalLiteratureDto = professionalLiteratureService.saveProfessionalLiterature(1L, professionalLiteratureDto);

        assertThat(updatedProfessionalLiteratureDto).isNotNull();
        assertThat(updatedProfessionalLiteratureDto.getName()).isEqualTo(professionalLiteratureDto.getName());
        assertThat(updatedProfessionalLiteratureDto.getScientificArea()).isEqualTo(professionalLiteratureDto.getScientificArea());

        verify(bookRepository, times(1)).save(any(Book.class));
        verify(plRepository, times(1)).save(any(ProfessionalLiterature.class));
    }
    
}
