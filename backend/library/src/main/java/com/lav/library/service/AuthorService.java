/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.service;

import com.lav.library.domain.Author;
import com.lav.library.domain.BookAuthor;
import com.lav.library.repository.AuthorRepository;
import com.lav.library.repository.BookAuthorRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Predstavlja servisnu klasu za upravljanje autorima
 * 
 * @author Lav Jovanovic
 */
@Service
public class AuthorService {
    
    @Autowired
    private AuthorRepository authorRepository;
    
    @Autowired
    private BookAuthorRepository bookAuthorRepository;
    
    /**
     * Vraca listu autora povezanih sa datim id knjige
     * 
     * @param id id knjige preko kojeg se pronalaze autori
     * @return lista sa svim knjigama,
     * ili prazna lista ako u biblioteci nema autora za dati id knjige
     */
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
