/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.repository;

import com.lav.library.domain.Author;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author Lav Jovanovic
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorRepositoryTest {
    

    @Autowired
    private AuthorRepository authorRepository;
    private Author author;
    
    @BeforeEach
    public void setUp() {
        
        author = new Author();
        author.setFirstName("Marko");
        author.setLastName("Markovic");
        
    }
    
    @AfterEach
    public void tearDown() {
        
        authorRepository.deleteAll();
        author = null;
        
    }

    
    @Test
    public void saveAuthorTest(){
        
        Author savedAuthor = authorRepository.save(author);
        assertEquals(savedAuthor.getFirstName(), author.getFirstName());
        assertEquals(savedAuthor.getLastName(), author.getLastName());
        
    }
    
    @Test
    public void getAllAuthorsTest(){
        
        Author author2 = new Author();
        author2.setFirstName("Nikola");
        author2.setLastName("Nikolic");
        authorRepository.save(author);
        authorRepository.save(author2);
        
        List<Author> authors = authorRepository.findAll();
        
        assertEquals(author.getFirstName(), authors.get(0).getFirstName());
        assertEquals(author.getLastName(), authors.get(0).getLastName());
        assertEquals(author2.getFirstName(), authors.get(1).getFirstName());
        assertEquals(author2.getLastName(), authors.get(1).getLastName());
        
    }
    
    @Test
    public void getAuthorByIdTest(){
        
        Author savedAuthor = authorRepository.save(author);
        
        Optional<Author> optionalAuthor = authorRepository.findById(savedAuthor.getAuthorId());
        
        Author author = optionalAuthor.get();
        
        assertEquals(author.getAuthorId(), savedAuthor.getAuthorId());
        assertEquals(savedAuthor.getFirstName(), author.getFirstName());
        assertEquals(author.getLastName(), savedAuthor.getLastName());
        
    }
    
    @Test
    public void findByFirstNameAndLastNameTest(){
        
        authorRepository.save(author);
        List<Author> authors = authorRepository.findByFirstNameAndLastName(author.getFirstName(), author.getLastName());
        
        assertEquals(author.getFirstName(), authors.get(0).getFirstName());
        assertEquals(author.getLastName(), authors.get(0).getLastName());
        
    }
    
    
}
