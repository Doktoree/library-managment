/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lav.library.domain.Author;
import jakarta.persistence.Column;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
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
     * Ime knjige kao String, ne sme biti null i sme biti prazan String
     */
    @NotEmpty(message = "Name is required!")
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
     * Zanr knjige(beletristike) kao String, ne sme biti null i ne sme biti prazan String
     */
    @NotEmpty(message = "Genre is required!")
    private String genre;
    
    /**
     * Tema knjige(beletristike) kao String, ne sme biti null i ne sme biti prazan String
     */
    @NotEmpty(message = "Theme is required!")
    private String theme;
    
    /**
     * Osvojene nagrade knjige(beletristike) kao String, ne sme biti null i ne sme biti prazan String
     */
    @NotEmpty(message = "Won prizes is required!")
    private String wonPrizes;
    
    /**
     * Lista autora ciji elementi su instance klase Author
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
