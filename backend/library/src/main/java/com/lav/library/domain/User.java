/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Predstavlja korisnika koji se loguje na sistem
 * Korisnik ima id, korisnicko ime, sifru, ime, prezime
 * 
 * @author Lav Jovanovic
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    
    /**
     * Jedinstveni identifikator Korisnika kao Long
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", columnDefinition = "BIGINT UNSIGNED")
    private long userId;
    
    /**
     * Korisnicko ime korisnika kao String, ne sme biti null
     */
    @Column(name = "username", nullable = false)
    private String username;
    
    /**
     * Sifra korisnika kao String, ne sme biti null
     */
    @Column(name = "password", nullable = false)
    private String password;
    
    /**
     * Ime korisnika kao String, ne sme biti null
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;
    
    /**
     * Prezime korisnika kao String, ne sme biti null
     */
    @Column(name = "last_name", nullable = false)   
    private String prezime;
    
}
