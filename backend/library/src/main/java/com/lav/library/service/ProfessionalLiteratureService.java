/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.service;

import com.lav.library.domain.Author;
import com.lav.library.domain.Book;
import com.lav.library.domain.BookAuthor;
import com.lav.library.domain.ProfessionalLiterature;
import com.lav.library.dto.AuthorDto;
import com.lav.library.dto.BookDto;
import com.lav.library.dto.ProfessionalLiteratureDto;
import com.lav.library.ids.BookAuthorId;
import com.lav.library.mapper.AuthroMapper;
import com.lav.library.mapper.BookMapper;
import com.lav.library.mapper.ProfessionalLiteratureMapper;
import com.lav.library.repository.AuthorRepository;
import com.lav.library.repository.BookAuthorRepository;
import com.lav.library.repository.BookRepository;
import com.lav.library.repository.ProfessionalLiteratureRepository;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.AEADBadTagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lav
 */
@Service
public class ProfessionalLiteratureService {

    @Autowired
    private ProfessionalLiteratureRepository plRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookAuthorRepository bookAuthorRepository;


    public ProfessionalLiteratureDto createProfessionalLiterature(ProfessionalLiteratureDto dto) {

        BookDto bookDto = ProfessionalLiteratureMapper.mapToBookDto(dto);
        Book savedBook = bookRepository.save(BookMapper.mapToBook(bookDto));
        ProfessionalLiterature pl = ProfessionalLiteratureMapper.mapToProfessionalLiterature(dto, savedBook);
        ProfessionalLiterature savedPl = plRepository.save(pl);
        List<Author> authors = dto.getAuthors();
        List<Author> savedAuthors = new ArrayList<>();

        for (Author a : authors) {

            Author aut = checkAuthor(a);

            if (aut == null) 
                aut = authorRepository.save(a);
            
            savedAuthors.add(aut);
            BookAuthor bookAuthor = new BookAuthor();
            BookAuthorId id = new BookAuthorId(savedBook, aut);
            bookAuthor.setId(id);
            bookAuthorRepository.save(bookAuthor);

        }
        
        
        return ProfessionalLiteratureMapper.mapToProfessionalLiteratureDto(savedPl, savedBook, savedAuthors);
    }

    public Author checkAuthor(Author author) {

        List<Author> authors = authorRepository.findByFirstNameAndLastName(author.getFirstName(), author.getLastName());

        if (authors.size() == 0) {
            return null;
        }

        return authors.get(0);

    }

}