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
 *
 * @author Lav
 */
public class ProfessionalLiteratureMapper {
    
    public static BookDto mapToBookDto(ProfessionalLiteratureDto professionalLiteratureDto){
        
        
        return new BookDto(
                professionalLiteratureDto.getBookId(),
                professionalLiteratureDto.getName(),
                professionalLiteratureDto.getYear(),
                professionalLiteratureDto.isTaken()
        );
        
    }
    
    public static ProfessionalLiterature mapToProfessionalLiterature(ProfessionalLiteratureDto professionalLiteratureDto, Book book){
        
        
        return new ProfessionalLiterature(
                professionalLiteratureDto.getBookId(),
                book,
                professionalLiteratureDto.getScientificArea()
                
        );
        
    }
    
    
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
