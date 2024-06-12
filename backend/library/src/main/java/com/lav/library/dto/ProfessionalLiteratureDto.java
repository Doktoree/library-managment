/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lav.library.domain.Author;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.util.ArrayList;
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
     * Ime knjige kao String, ne sme biti null i ne sme biti prazan String
     */
    @NotEmpty(message = "Name is required!")
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
     * Naucna oblast knjige(strucne literature) kao String,, ne sme biti null i ne sme biti prazan String
     */
    @NotEmpty(message = "Scientific area is required!")
    private String scientificArea;
    
    /**
     * Lista autora knjige(strucne literature) ciji elementi su instance klase Author
     */
    private List<Author> authors = new ArrayList<>();
    
    /**
     * Proverava validnost godine izdavanja knjige
     * 
     * @return boolean True ako je godina veca ili jednako od nula i ako je godina manja od trenutne godine,
     * False u ostalim slucajevima
     * 
     */
    @JsonIgnore
    @AssertTrue(message = "Year must be a valid number")
    public boolean isYearValid() {
        int currentYear = LocalDate.now().getYear();
        return year >= 0 && year <= currentYear;
    }
    
}
