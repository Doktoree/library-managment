/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.mapper;

import com.lav.library.domain.Author;
import com.lav.library.dto.AuthorDto;

/**
 *
 * @author Lav
 */
public class AuthroMapper {
    
    public static AuthorDto mapToAuthorDto(Author author){
        
        return new AuthorDto(
        
                author.getAuthorId(),
                author.getFirstName(),
                author.getLastName()
        
        );
        
    }
    
    public static Author mapToAuthor(AuthorDto authorDto){
        
        return new Author(
        
                authorDto.getAuthorId(),
                authorDto.getFirstName(),
                authorDto.getLastName()
                
        
        );
        
    }
    
}
