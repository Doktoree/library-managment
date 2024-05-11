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
import java.util.Optional;
import javax.crypto.AEADBadTagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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
    
    public ProfessionalLiteratureDto saveProfessionalLiterature(Long id, ProfessionalLiteratureDto dto){
        Optional<ProfessionalLiterature> optionalPl = plRepository.findById(id);
        Optional<Book> optionalBook = bookRepository.findById(id);
        
        if(optionalPl.isPresent() && optionalBook.isPresent()){
            
            ProfessionalLiterature updatedPl = optionalPl.get();
            updatedPl.setScientificArea(dto.getScientificArea());
            Book updatedBook = optionalBook.get();
            updatedBook.setName(dto.getName());
            updatedBook.setTaken(dto.isTaken());
            updatedBook.setYear(dto.getYear());
            ProfessionalLiterature savedPl = plRepository.save(updatedPl);
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
            
            return ProfessionalLiteratureMapper.mapToProfessionalLiteratureDto(savedPl, savedBook, authors);
            
        }
        
        
        return null;
    }

    
    public List<Object> getBooks(BookDto dto){
        
        Book book = BookMapper.mapToBook(dto);
        List<Book> books = bookRepository.findAll(Example.of(book));
        List<Object> objBooks = new ArrayList<>();
        
        for(Book b: books){
            
            Optional<ProfessionalLiterature> optinalPl = plRepository.findById(b.getBookId());
            
            if(optinalPl.isPresent()){
                
                ProfessionalLiterature pl = optinalPl.get();
                List<BookAuthor> bookAuthors = bookAuthorRepository.findById_BookBookId(b.getBookId());
                List<Author> authors = new ArrayList<>();
                
                for(BookAuthor ba: bookAuthors){
                    
                    Optional<Author> a = authorRepository.findById(ba.getId().getAuthor().getAuthorId());
                    authors.add(a.get());
                }
                
                ProfessionalLiteratureDto plDto = ProfessionalLiteratureMapper.mapToProfessionalLiteratureDto(pl, b, authors);
                                
            }
            
            else{
                
                
            
            if(optinalPl.isPresent()){
                
                ProfessionalLiterature pl = optinalPl.get();
                List<BookAuthor> bookAuthors = bookAuthorRepository.findById_BookBookId(b.getBookId());
                List<Author> authors = new ArrayList<>();
                
                for(BookAuthor ba: bookAuthors){
                    
                    Optional<Author> a = authorRepository.findById(ba.getId().getAuthor().getAuthorId());
                    authors.add(a.get());
                }
                
                ProfessionalLiteratureDto plDto = ProfessionalLiteratureMapper.mapToProfessionalLiteratureDto(pl, b, authors);
                                
            }
                
                
            }
            
            
        }
        
        return null;
    }
    
}
