/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO klasa koja predstavlja informacije o stavki zaduzenja koja postoji u zaduzenju
 * LoanItemDto ima id stavke zaduzenja, id zaduzenja, status i id knjige
 * 
 * @author Lav Jovanovic
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoanItemDto {
    
    /**
     * Jedinstveni identifikator Stavke zaduzenja kao Long
     */
    private Long loanItemId;
    
    /**
     * Jedinstveni identifikator Zaduzenja kao Long
     */
    private Long loanId;
    
    /**
     * Status stavke zaduzenja kao Stirng
     */
    private String status = "not returned";
    
    /**
     * Jedinstveni identifikator Knjige kao Long
     */
    @NotNull(message = "Book id is required!")
    private Long bookId;
    
}
