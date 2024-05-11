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
     public List<Object> getBooks(String bookName, List<AuthorDto> authorsDto){
        
         Book book = new Book();
         book.setName(bookName);
         List<Book> books = new ArrayList<>();
         List<Author> authors = authorsDto.stream().map(AuthroMapper::mapToAuthor).collect(Collectors.toList());
         if(!book.getName().isEmpty() && book.getName() != null){            
             books = bookRepository.findAll(Example.of(book));
         }
         
         List<Book> goodBooks = new ArrayList<>();
         
         for(int i = 0; i<books.size(); i++){
             
             List<BookAuthor> ba = bookAuthorRepository.findById_BookBookId(book.getBookId());
             
             
         }
        
         
         
        
         return null;
    }
    
    public boolean deleteBook(Long id){
        
        Optional<Book> optionalBook = bookRepository.findById(id);
        
        if(!optionalBook.isPresent())
            return false;
        
        Book book = optionalBook.get();
        
        if(book.isTaken()){
         
            System.out.println("!!!!!!!!!!!!!!! taken");
            return false;
        }
            
        
        bookRepository.deleteById(id);
        
        return true;
        
    }
   
}
