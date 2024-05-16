/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO klasa koja predstavlja informacije o Autoru
 * AuthorDto ima id autora, ime autora, prezime autora
 * 
 * @author Lav Jovanovic
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {

    /**
     * Jedinstveni identifikator Autora kao Long
     */
    Long authorId;
    
    /**
     * Ime autora kao String
     */
    String firstName;
    
    /**
     * Prezime autora kao String
     */
    String lastName;

}
