/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Predstavlja knjigu koja se nalazi u biblioteci.
 * Knjiga ima id, ime, godinu izdavanja i jeZauzeta
 * 
 * @author Lav Jovanovic
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {
    
    /**
     * Jedinstveni identifikator Knjige kao Long
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT UNSIGNED")
    private Long bookId;
    
    /**
     * Ime knjige kao String, ne sme biti null
     */
    @Column(name = "name", nullable = false)
    private String name;
    
    /**
     * Godina izdavanja knjige kao int, ne sme biti null
     */
    @Column(name = "year_of_publication", nullable = false)
    private int year;
    
    /**
     * Da li je knjiga zauzeta kao boolean, ne sme biti null
     */
    @Column(name = "is_taken", nullable = false)
    private boolean isTaken = false;
    
}
