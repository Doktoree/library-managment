/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.internal.build.AllowSysOut;

/**
 * Predstavlja zaduzenje koje clan moze da ima u biblioteci
 * Zaduzenje ima id, datum pocetka zaduzenja, datum zavrsetka zaduzenja i clana
 * 
 * @author Lav Jovanovic
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Loan {
    
    /**
     * Jedinstveni identifikator Zaduzenja kao Long
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id", columnDefinition = "BIGINT UNSIGNED")
    private Long loanId;
    
    /**
     * Datum pocetka zaduzenja kao LocalDateTime, ne sme biti null
     */
    @Column(name = "start_date_of_loan", nullable = false)
    private LocalDateTime startDateOfLoan;
    
    /**
     * Datum zavrsetka zaduzenja kao LocalDateTime, ne sme biti null
     */
    @Column(name = "end_date_of_loan", nullable = false)
    private LocalDateTime endDateOfLoan;
    
    /**
     * Clan koji ima zaduzenje kao instanca klase Member, ne sme biti null
     */
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
    
}
