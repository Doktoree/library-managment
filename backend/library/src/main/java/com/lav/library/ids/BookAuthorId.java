/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.ids;

import com.lav.library.domain.Author;
import com.lav.library.domain.Book;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Klasa koja predstavlja jedinstveni identifikator za knjigu autora
 * Ovaj identifikator se sastoji od instance klase Book i instance klase Author
 * Koristi se kao primarni ključ u drugim entitetima
 * 
 * @author Lav Jovanovic
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@EqualsAndHashCode
public class BookAuthorId implements Serializable{
    
    /**
     * Knjiga koja je deo ovog identifikatora
     */
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    
    /**
     * Autor koji je deo ovog identifikatora
     */
    @ManyToOne
    @JoinColumn(name = "author_id") 
    private Author author;
    
}
