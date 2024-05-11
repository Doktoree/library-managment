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
 *
 * @author Lav
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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "loan_item_id")
    private Long loanItemId;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;
    
    
    private String status = "not returned";

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

}
