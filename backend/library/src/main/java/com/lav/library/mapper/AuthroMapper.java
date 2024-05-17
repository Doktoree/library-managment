/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.mapper;

import com.lav.library.domain.Author;
import com.lav.library.dto.AuthorDto;

/**
 * Klasa koja se koristi za mapiranje izmedju objekata klase Author i AuthorDto
 * 
 * @author Lav Jovanovic
 */
public class AuthroMapper {
    
    /**
     * Mapira objekat klase Author na AuthorDto
     * 
     * @param author Autor koji se mapira kao instanca klase Author
     * @return mapirani objekat kao instanca klase AuthorDto
     */
    public static AuthorDto mapToAuthorDto(Author author){
        
        return new AuthorDto(
        
                author.getAuthorId(),
                author.getFirstName(),
                author.getLastName()
        
        );
        
    }
    /**
     * Mapira objekat klase AuthorDto na Author
     * 
     * @param authorDto DTO koji se mapira kao instanca klase AuthorDto
     * @return mapirani objekat kao instanca klase Author
     */
    public static Author mapToAuthor(AuthorDto authorDto){
        
        return new Author(
        
                authorDto.getAuthorId(),
                authorDto.getFirstName(),
                authorDto.getLastName()
                
        
        );
        
    }
    
}
