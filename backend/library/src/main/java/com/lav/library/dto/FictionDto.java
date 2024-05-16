/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.dto;

import com.lav.library.domain.Author;
import jakarta.persistence.Column;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.internal.build.AllowSysOut;

/**
 *
 * DTO klasa koja predstavlja informacije o knjizi i njenoj vrsti beletristici, kao i o Autorima knjige
 * FictionDto ima id, ime, godinu izdavanja, jeZauzeta, zanr, temu, osvojene nagrade i listu Autora
 * 
 * @author Lav Jovanovic
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FictionDto {

    /**
     * Jedinstveni identifikator Knjige kao Long
     */
    private Long bookId;
    
    /**
     * Ime knjige kao String
     */
    private String name;
    
    /**
     * Datum izdavanja knjige kao int
     */
    private int year;
    
    /**
     * Da li je knjiga zauzeta kao boolean
     */
    private boolean isTaken;
    
    /**
     * Zanr knjige(beletristike) kao String
     */
    private String genre;
    
    /**
     * Tema knjige(beletristike) kao String
     */
    private String theme;
    
    /**
     * Osvojene nagrade knjige(beletristike) kao String
     */
    private String wonPrizes;
    
    /**
     * Lista autora ciji elementi su instance klase Author
     */
    private List<Author> authors;

}
