/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.dto;

import com.lav.library.domain.Author;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO klasa koja predstavlja informacije o strucnoj literaturi
 * ProfessionalLiteratureDto ima id knjige, ime, godinu izdavanja, jeZauzeta, naucnu oblast i listu Autora
 * 
 * @author Lav Jovanovic
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProfessionalLiteratureDto {
    
    /**
     * Jedinstveni identifikator Knjige kao Long
     */
    private Long bookId;
    
    /**
     * Ime knjige kao String
     */
    private String name;
    
    /**
     * Godina izdavanja knjige kao int
     */
    private int year;
    
    /**
     * Da li je knjiga zauzeta kao boolean
     */
    private boolean isTaken;
    
    /**
     * Naucna oblast knjige(strucne literature) kao String
     */
    private String scientificArea;
    
    /**
     * Lista autora knjige(strucne literature) ciji elementi su instance klase Author
     */
    private List<Author> authors;
    
}
