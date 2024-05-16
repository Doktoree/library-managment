/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Predstavlja fikciju koja može biti deo biblioteke.
 * Fikcija je povezana sa knjigom, ima žanr, temu i informaciju o osvojenim nagradama.
 * @author Lav
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fiction {

    /**
     * Jedinstveni identifikator Fikcije kao Long
     */
    @Id
    private Long id;
    
    /**
     * Knjiga koja predstavlja fikciju kao instanca klase Book
     */
    @OneToOne
    @MapsId
    private Book book;
    
    /**
     * Zanr fikcija kao String, ne sme biti null
     */
    @Column(nullable = false)
    private String genre;
    
    /**
     * Tema fikcije kao String, ne sme biti null
     */
    @Column(nullable = false)
    private String theme;
    
    /**
     * Osvojene nagrade kao String, ne sme biti null
     */
    @Column(name = "won_prizes", nullable = false)
    private String wonPrizes;
    
}
