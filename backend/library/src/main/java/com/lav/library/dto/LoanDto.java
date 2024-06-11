/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.dto;

import com.lav.library.domain.LoanItem;
import com.lav.library.domain.Member;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.internal.build.AllowSysOut;

/**
 * DTO klasa koja predstavlja informacije o zaduzenju koje clan ima u biblioteci
 * LoanDto ima id, datum pocetka zaduzenja, datum zavrsetka zaduzenja, id clana i listu stavki zaduzenja
 * 
 * @author Lav Jovanovic
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoanDto {
    
    /**
     * Jedinstveni identifikator Zaduzenja kao Long
     */
    private Long loanId;
    
    /**
     * Datum pocetka zaduzenja kao LocalDateTime
     * Pocetna vrednost jeste sadasnje vreme
     */
    private LocalDateTime startDateOfLoan = LocalDateTime.now();
    
    /**
     * Datum zavrsetka zaduzenja kao LocalDateTime
     * Pocetna vrednost jeste vrednost datuma pocetka zaduzenja plus 20 dana
     */
    private LocalDateTime endDateOfLoan = startDateOfLoan.plusDays(20);
    
    /**
     * Jedinstveni identifikator Clana kao Long, ne sme biti null
     */
    @NotNull(message = "Member ID is required")
    private Long memberId;
    
    /**
     * Lista stavki zaduzenja ciji elementi su instance klase LoanItemDto, ne sme biti null i ne sme biti prazna lista
     */
    @NotNull(message = "Loan items are required")
    @Size(min = 1, message = "There must be at least one loan item")
    private List<LoanItemDto> loanItems;
    
}
