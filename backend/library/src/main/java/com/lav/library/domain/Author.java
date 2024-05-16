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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.internal.build.AllowSysOut;

/**
 * Predstavlja autora knjige. 
 * Autor ima ime i prezime
 *
 * @author Lav Jovanovic
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    /**
     * Jedinstveni identifikator Autora kao Long
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id", columnDefinition = "BIGINT UNSIGNED")
    Long authorId;

    /**
     * Ime autora kao String, ne sme biti null
     */
    @Column(nullable = false)
    String firstName;
    /**
     * Prezime autora kao String
     */
    String lastName;

}
