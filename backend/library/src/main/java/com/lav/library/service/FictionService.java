/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.service;

import com.lav.library.domain.Author;
import com.lav.library.domain.Book;
import com.lav.library.domain.BookAuthor;
import com.lav.library.domain.Fiction;
import com.lav.library.domain.ProfessionalLiterature;
import com.lav.library.dto.BookDto;
import com.lav.library.dto.FictionDto;
import com.lav.library.dto.ProfessionalLiteratureDto;
import com.lav.library.ids.BookAuthorId;
import com.lav.library.mapper.BookMapper;
import com.lav.library.mapper.FictionMapper;
import com.lav.library.mapper.ProfessionalLiteratureMapper;
import com.lav.library.repository.AuthorRepository;
import com.lav.library.repository.BookAuthorRepository;
import com.lav.library.repository.BookRepository;
import com.lav.library.repository.FictionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lav
 */
@Service
public class FictionService {
    
    
    @Autowired
    private FictionRepository fictionRepository;
    
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookAuthorRepository bookAuthorRepository;
    
    
    public FictionDto createFiction(FictionDto dto) {

        BookDto bookDto = FictionMapper.mapToBookDto(dto);
        Book savedBook = bookRepository.save(BookMapper.mapToBook(bookDto));
        Fiction fiction = FictionMapper.mapToFiction(dto, savedBook);
        Fiction savedFiction = fictionRepository.save(fiction);
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
        
        
        return FictionMapper.mapToFictionDto(savedFiction, savedBook, savedAuthors);
    }

    public Author checkAuthor(Author author) {

        List<Author> authors = authorRepository.findByFirstNameAndLastName(author.getFirstName(), author.getLastName());

        if (authors.size() == 0) {
            return null;
        }

        return authors.get(0);

    }
    
    public FictionDto saveFiction(Long id, FictionDto dto){
        Optional<Fiction> optionalFiction = fictionRepository.findById(id);
        Optional<Book> optionalBook = bookRepository.findById(id);
        
        if(optionalFiction.isPresent() && optionalBook.isPresent()){
            
            Fiction updatedFiction = optionalFiction.get();
            updatedFiction.setGenre(dto.getGenre());
            updatedFiction.setTheme(dto.getTheme());
            updatedFiction.setWonPrizes(dto.getWonPrizes());
            Book updatedBook = optionalBook.get();
            updatedBook.setName(dto.getName());
            updatedBook.setTaken(dto.isTaken());
            updatedBook.setYear(dto.getYear());
            Fiction savedFiction = fictionRepository.save(updatedFiction);
            Book savedBook = bookRepository.save(updatedBook);
            
            List<BookAuthor> bookAuthors = bookAuthorRepository.findById_BookBookId(savedBook.getBookId());
            
            for(BookAuthor ba: bookAuthors){
                
                bookAuthorRepository.delete(ba);
                
            }
            
            List<Author> authors = dto.getAuthors();
            
            for(Author a: authors ){
                
                Author aut = checkAuthor(a);
                
                if(aut == null)
                    aut = authorRepository.save(a);
                
                BookAuthorId baid = new BookAuthorId(savedBook, aut);
                BookAuthor savedBa = bookAuthorRepository.save(new BookAuthor(baid));
                
            }
            
            return FictionMapper.mapToFictionDto(savedFiction, savedBook, authors);
            
        }
        
        
        return null;
    }
    
}
