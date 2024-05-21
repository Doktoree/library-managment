/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.repository;

import com.lav.library.domain.Book;
import com.lav.library.domain.ProfessionalLiterature;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author Lav
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProfessionalLiteratureRepositoryTest {
    
    @Autowired
    private ProfessionalLiteratureRepository professionalLiteratureRepository;

    @Autowired
    private BookRepository bookRepository;

    private ProfessionalLiterature professionalLiterature;
    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book();
        book.setName("Advanced Programming");
        book.setYear(2020);
        book.setTaken(false);
        book = bookRepository.save(book);

        professionalLiterature = new ProfessionalLiterature();
        professionalLiterature.setBook(book);
        professionalLiterature.setScientificArea("Computer Science");
    }

    @AfterEach
    public void tearDown() {
        professionalLiteratureRepository.deleteAll();
        bookRepository.deleteAll();
        professionalLiterature = null;
        book = null;
    }

    @Test
    public void saveProfessionalLiteratureTest() {
        ProfessionalLiterature savedProfessionalLiterature = professionalLiteratureRepository.save(professionalLiterature);
        assertNotNull(savedProfessionalLiterature);
        assertEquals(professionalLiterature.getBook().getBookId(), savedProfessionalLiterature.getBook().getBookId());
        assertEquals(professionalLiterature.getScientificArea(), savedProfessionalLiterature.getScientificArea());
    }

    @Test
    public void getAllProfessionalLiteratureTest() {
        professionalLiteratureRepository.save(professionalLiterature);
        List<ProfessionalLiterature> professionalLiteratures = professionalLiteratureRepository.findAll();
        assertFalse(professionalLiteratures.isEmpty());
        assertEquals(1, professionalLiteratures.size());
    }

    @Test
    public void findProfessionalLiteratureByIdTest() {
        ProfessionalLiterature savedProfessionalLiterature = professionalLiteratureRepository.save(professionalLiterature);
        Optional<ProfessionalLiterature> foundProfessionalLiterature = professionalLiteratureRepository.findById(savedProfessionalLiterature.getId());
        assertTrue(foundProfessionalLiterature.isPresent());
        assertEquals(savedProfessionalLiterature.getId(), foundProfessionalLiterature.get().getId());
    }

    @Test
    public void deleteProfessionalLiteratureTest() {
        ProfessionalLiterature savedProfessionalLiterature = professionalLiteratureRepository.save(professionalLiterature);
        professionalLiteratureRepository.delete(savedProfessionalLiterature);
        Optional<ProfessionalLiterature> deletedProfessionalLiterature = professionalLiteratureRepository.findById(savedProfessionalLiterature.getId());
        assertFalse(deletedProfessionalLiterature.isPresent());
    }
    
}
