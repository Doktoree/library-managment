/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.ids;

import com.lav.library.domain.Loan;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Klasa koja predstavlja jedinstveni identifikator za stavku zaduzenja
 * Ovaj identifikator se sastoji od identifikatora stavke zaduzenja i instance klase Loan
 * Koristi se kao primarni ključ u drugim entitetima
 * 
 * @author Lav Jovanovic
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class LoanItemId implements Serializable {

    /**
     * Jedinstveni identifikator za stavku zaduzenja kao Long
     */
    private Long loanItemId;
    
    /**
     * Zaduzenje koje je deo identifikatora kao instanca klase Loan
     */
    private Loan loan;

}
