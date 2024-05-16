/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Predstavlja tip knjige koji postoji u biblioteci
 * Strucna literatura ima id, knjigu i naucnu oblast
 * 
 * @author Lav Jovanovic
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "professional_literature")
public class ProfessionalLiterature {
    
    /**
     * Jedinstveni identifikator Strucne literature kao Long
     */
    
    @Id
    private Long id;
    
    /**
     * Knjiga kojoj strucna literatura pripada kao instanca klase Book
     */   
    @OneToOne
    @MapsId
    private Book book;
    
    
    /**
     * Naucna oblast kao String
     */
    @Column(name = "scientific_area", nullable = false)
    private String scientificArea;
    
}
