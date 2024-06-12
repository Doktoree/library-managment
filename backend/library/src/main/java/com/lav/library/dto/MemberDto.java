/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
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
     * Ime autora kao String, ne sme biti null i ne sme biti prazan String
     */
    @NotEmpty(message = "First name is required!")
    private String firstName;

    /**
     * Prezime autora kao String, ne sme biti null i ne sme biti prazan String
     */
    @NotEmpty(message = "Last name is required!")
    private String lastName;

    /**
     * Adresa autora kao String, ne sme biti null i ne sme biti prazan String
     */
    @NotEmpty(message = "Adress is required!")
    private String adress;

    /**
     * Broj telefona autora kao String, ne sme biti null i ne sme biti prazan String
     */
    @NotEmpty(message = "Phone number is required!")
    private String phoneNumber;

    /**
     * Datum rodjenja autora kao LocalDate, ne sme biti null i ne sme biti datum u buducnosti
     */
    @NotNull(message = "Birth date is required")
    @Past(message = "Birth date must be a valid date in the past")
    private LocalDate birthDate;

}
