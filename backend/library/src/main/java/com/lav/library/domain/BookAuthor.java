/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.domain;

import com.lav.library.ids.BookAuthorId;
import ch.qos.logback.core.joran.spi.NoAutoStart;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.internal.build.AllowSysOut;

/**
 * Predstavlja vezu između knjige i autora u biblioteci.
 * Svaka veza sadrzi identifikator knjige i identifikator autora.
 * @author Lav Jovanovic
 */


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book_author")
public class BookAuthor {
    
    /**
     * Identifikator kao instanca klase BookAuthorId
     */
    @EmbeddedId
    private BookAuthorId id;
    
    
}
