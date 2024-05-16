/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.dto;

import jakarta.persistence.Column;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO klasa koja predstavlja informacije o clanu biblioteke
 * MemberDto ima id, ime, prezime, adresu, broj telefona, datum rodjenja
 * 
 * @author Lav Jovanovic
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    /**
     * Jedinstveni identifikator Clana kao Long
     */
    private Long memberId;

    /**
     * Ime autora kao String
     */
    private String firstName;

    /**
     * Prezime autora kao String
     */
    private String lastName;

    /**
     * Adresa autora kao String
     */
    private String adress;

    /**
     * Broj telefona autora kao String
     */
    private String phoneNumber;

    /**
     * Datum rodjenja autora kao LocalDate
     */
    private LocalDate birthDate;

}
