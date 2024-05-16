/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.internal.build.AllowSysOut;

/**
 * DTO klasa koja predstavlja informacije o knjizi
 * BookDto ima id, ime, godinu izdavanja i jeZauzeta
 * 
 * @author Lav Jovanovic
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookDto {

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



}
