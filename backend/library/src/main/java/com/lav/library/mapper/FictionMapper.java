/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.mapper;

import com.lav.library.domain.Author;
import com.lav.library.domain.Book;
import com.lav.library.domain.Fiction;
import com.lav.library.domain.ProfessionalLiterature;
import com.lav.library.dto.BookDto;
import com.lav.library.dto.FictionDto;
import com.lav.library.dto.ProfessionalLiteratureDto;
import java.util.List;

/**
 *
 * @author Lav
 */
public class FictionMapper {
    
    public static BookDto mapToBookDto(FictionDto fictionDto){
        
        
        return new BookDto(
                fictionDto.getBookId(),
                fictionDto.getName(),
                fictionDto.getYear(),
                fictionDto.isTaken()
        );
        
    }
    
    public static Fiction mapToFiction(FictionDto fictionDto, Book book){
        
        
        return new Fiction(
                fictionDto.getBookId(),
                book,
                fictionDto.getGenre(),
                fictionDto.getTheme(),
                fictionDto.getWonPrizes()
                
        );
        
    }
    
    
    public static FictionDto mapToFictionDto(Fiction fiction, Book book, List<Author> authors){
        
        return new FictionDto(
        
                fiction.getId(),
                book.getName(),
                book.getYear(),
                book.isTaken(),
                fiction.getGenre(),
                fiction.getTheme(),
                fiction.getWonPrizes(),
                authors
        
        
        );
        
    }
    
}
