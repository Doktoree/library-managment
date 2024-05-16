/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Predstavlja clana biblioteke
 * Clan ima id, ime, prezime, adresu, broj telefona i datum rodjenja
 * 
 * @author Lav Jovanovic
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member {
    
    /**
     * Jedinstveni identifikator Clana kao Long
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", columnDefinition = "BIGINT UNSIGNED")
    private Long memberId;
    
    /**
     * Ime clana kao String, ne sme biti null
     */
    
    @Column(name = "first_name", nullable = false)
    private String firstName;
    
    /**
     * Prezime clana kao String, ne sme biti null
     */
    @Column(name = "last_name", nullable = false)
    private String lastName;
    
    /**
     * Adresa clana kao String, ne sme biti null
     */
    @Column(nullable = false)
    private String adress;
    
    /**
     * Broj telefona clan kao String, ne sme biti null
     */
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    
    /**
     * Datum rodjenja clana kao LocalDate, ne sme biti null
     */
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;
    
    /**
     * Set zaduzenja koji su povezani sa clanom
     */
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Loan> loans;
    
}
