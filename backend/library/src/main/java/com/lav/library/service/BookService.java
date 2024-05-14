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
import com.lav.library.dto.FictionDto;
import com.lav.library.dto.ProfessionalLiteratureDto;
import com.lav.library.mapper.AuthroMapper;
import com.lav.library.mapper.BookMapper;
import com.lav.library.mapper.FictionMapper;
import com.lav.library.mapper.ProfessionalLiteratureMapper;
import com.lav.library.repository.AuthorRepository;
import com.lav.library.repository.BookAuthorRepository;
import com.lav.library.repository.BookRepository;
import com.lav.library.repository.FictionRepository;
import com.lav.library.repository.ProfessionalLiteratureRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lav
 */
@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private ProfessionalLiteratureRepository plRepo;
    
    @Autowired
    private FictionRepository fictionRepository;
    
    @Autowired
    private BookAuthorRepository bookAuthorRepository;
    
    @Autowired
    private AuthorRepository authorRepository;
    
    public List<BookDto> getAllBooks(){
        
        List<Book> allBooks = bookRepository.findAll();
        
        return allBooks.stream().map(BookMapper::mapToBookDto).collect(Collectors.toList());
    }
    
    public BookDto createBook(BookDto bookDto){
        
        Book book = BookMapper.mapToBook(bookDto);
        Book createdBook = bookRepository.save(book);
       
        return BookMapper.mapToBookDto(createdBook);
    }
    
    public Object getBook(Long id, List<Author> authors){
        
        Optional<Book> optionalBook = bookRepository.findById(id);
        
        if(!optionalBook.isPresent())
            return null;
        
        Book book = optionalBook.get();
        Optional<ProfessionalLiterature> optionalPl = plRepo.findById(id);
        
        if(optionalPl.isPresent()){
            
            return ProfessionalLiteratureMapper.mapToProfessionalLiteratureDto(optionalPl.get(), book, authors);
            
        }
        
        else{
            
            Optional<Fiction> optionalFiction = fictionRepository.findById(id);
            Fiction fiction = optionalFiction.get();
            
            return FictionMapper.mapToFictionDto(fiction, book, authors);
            
        }
        
     
        
    }
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     public List<Object> getBooks(BookDto bookDto){
        
         
         Book book = BookMapper.mapToBook(bookDto);
         List<Book> books;
         
         if(book.getYear() == 0 && book.getName()== null)
             return new ArrayList();
         
         if(book.getYear()!=0 && book.getName()!=null)
             books = bookRepository.findByYearAndName(bookDto.getYear(), bookDto.getName());
         
         else if(book.getYear()!=0)
             books = bookRepository.findByYear(bookDto.getYear());
         else
             books = bookRepository.findByName(bookDto.getName());
         
        
         List<Object> list = new ArrayList<>();
         
         for(Book b: books){
             
             Optional<ProfessionalLiterature> optionalPl = plRepo.findById(b.getBookId());
             if(optionalPl.isPresent()){
                 ProfessionalLiterature pl = optionalPl.get();
                 List<Author> authors = getAuthorsByBookId(pl.getId());
                 ProfessionalLiteratureDto dto = ProfessionalLiteratureMapper.mapToProfessionalLiteratureDto(pl, book, authors);
                 list.add(dto);
                 
             }
             else{
                 Optional<Fiction> optionalFiction = fictionRepository.findById(b.getBookId());
                 Fiction fiction = optionalFiction.get();
                 List<Author> authors = getAuthorsByBookId(fiction.getId());
                 FictionDto fictionDto = FictionMapper.mapToFictionDto(fiction, book, authors);
                 list.add(fictionDto);
                 
             }
             
         }
         
        
         return list;
    }
    
    public boolean deleteBook(Long id){
        
        Optional<Book> optionalBook = bookRepository.findById(id);
        
        if(!optionalBook.isPresent())
            return false;
        
        Book book = optionalBook.get();
        
        if(book.isTaken()){
         
            return false;
        }
            
        
        bookRepository.deleteById(id);
        
        return true;
        
    }
    
    public List<Author> getAuthorsByBookId(Long id){
        
        List<Author> authors = new ArrayList<>();
        List<BookAuthor> bookAuthors = bookAuthorRepository.findById_BookBookId(id);
        
        for(BookAuthor ba: bookAuthors){
            
            Optional<Author> optionalAuthor = authorRepository.findById(ba.getId().getBook().getBookId());
            if(optionalAuthor.isPresent())
                authors.add(optionalAuthor.get());
            
        }
        
        return authors;
    }
   
}
