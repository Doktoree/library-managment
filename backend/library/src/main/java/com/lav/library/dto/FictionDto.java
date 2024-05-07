/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.dto;

import com.lav.library.domain.Author;
import jakarta.persistence.Column;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.internal.build.AllowSysOut;

/**
 *
 * @author Lav
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FictionDto {

    private Long bookId;
    private String name;
    private int year;
    private boolean isTaken;
    private String genre;
    private String theme;
    private String wonPrizes;
    private List<Author> authors;

}
