/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.domain;

import com.lav.library.ids.LoanItemId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Predstavlja stavku zaduzenja koja se nalazi u zaduzenju
 * Stavka zaduzenja ima id, zaduzenje, status i knjigu
 * 
 * @author Lav Jovanovic
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "loan_item")
@ToString
@IdClass(LoanItemId.class)
public class LoanItem {

    /**
     * Jedinstveni identifikator stavke zaduzenja kao Long
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "loan_item_id")
    private Long loanItemId;
    
    /**
     * Zaduzenje u kome se nalazi stavka zaduzenja kao instanca klase Loan
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;
    
    /**
     * Status knjige kao String
     */
    private String status = "not returned";

    /**
     * Knjiga koja se nalazi u stavci zaduzenja kao instanca klase Book, ne sme biti null
     */
    
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

}
