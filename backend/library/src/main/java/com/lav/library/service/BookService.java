/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.service;

import com.lav.library.domain.Book;
import com.lav.library.dto.BookDto;
import com.lav.library.mapper.BookMapper;
import com.lav.library.repository.BookRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lav
 */
@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;
    
    public List<BookDto> getAllBooks(){
        
        List<Book> allBooks = bookRepository.findAll();
        
        return allBooks.stream().map(BookMapper::mapToBookDto).collect(Collectors.toList());
    }
    
    public BookDto createBook(BookDto bookDto){
        
        Book book = BookMapper.mapToBook(bookDto);
        Book createdBook = bookRepository.save(book);
       
        return BookMapper.mapToBookDto(createdBook);
    }
    
}
