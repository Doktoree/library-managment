/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.mapper;

import com.lav.library.domain.Author;
import com.lav.library.domain.Book;
import com.lav.library.domain.ProfessionalLiterature;
import com.lav.library.dto.BookDto;
import com.lav.library.dto.ProfessionalLiteratureDto;
import java.util.List;

/**
 * Klasa koja se koristi za mapiranje izmedju objekata klasa ProfessionalLiterature, ProfessionalLiteratureDto i Book
 * 
 * @author Lav Jovanovic
 */
public class ProfessionalLiteratureMapper {
    
    /**
     * Mapira objekat klase ProfessionalLiteratureDto na BookDto
     *
     * @param professionalLiteratureDto DTO objekat koji se mapira kao instanca klase ProfessionalLiteratureDto
     * @return mapirani objekat kao instanca klase BookDto
     */
    public static BookDto mapToBookDto(ProfessionalLiteratureDto professionalLiteratureDto){
        
        
        return new BookDto(
                professionalLiteratureDto.getBookId(),
                professionalLiteratureDto.getName(),
                professionalLiteratureDto.getYear(),
                professionalLiteratureDto.isTaken()
        );
        
    }
    
    /**
     * Mapira objekat klase ProfessionalLiteratureDto i objekat klase Book na ProfessionalLiterature
     *
     * @param professionalLiteratureDto DTO koji se mapira kao instanca klase ProfessionalLiteratureDto
     * @param book Knjiga koja se mapira kao instanca klase Book
     * @return mapirani objekat kao instanca klase ProfessionalLiterature
     */
    public static ProfessionalLiterature mapToProfessionalLiterature(ProfessionalLiteratureDto professionalLiteratureDto, Book book){
        
        
        return new ProfessionalLiterature(
                professionalLiteratureDto.getBookId(),
                book,
                professionalLiteratureDto.getScientificArea()
                
        );
        
    }
    
    /**
     * Mapira objekat klase ProfessionalLiterature, objekat klase Book i listu autora na ProfessionalLiteratureDto
     *
     * @param pl Strucna literatura koja se mapira kao instanca klase ProfessionalLiterature
     * @param book Knjiga koja se mapira kao instanca klase Book
     * @param authors Lista autora koja se mapira
     * @return mapirani objekat kao instanca klase ProfessionalLiteratureDto
     */
    public static ProfessionalLiteratureDto mapToProfessionalLiteratureDto(ProfessionalLiterature pl, Book book, List<Author> authors){
        
        return new ProfessionalLiteratureDto(
        
                pl.getId(),
                book.getName(),
                book.getYear(),
                book.isTaken(),
                pl.getScientificArea(),
                authors
        
        
        );
        
    }
    
    
    
}
