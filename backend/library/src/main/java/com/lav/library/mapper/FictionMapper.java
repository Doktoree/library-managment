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
 * Klasa koja se koristi za mapiranje izmedju objekata klasa Fiction, FictionDto i Book
 * 
 * @author Lav Jovanovic
 */
public class FictionMapper {
    
    /**
     * Mapira objekat klase FictionDto na BookDto
     *
     * @param fictionDto DTO objekat koji se mapira kao instanca klase FictionDto
     * @return mapirani objekat kao instanca klase BookDto
     */
    public static BookDto mapToBookDto(FictionDto fictionDto){
        
        
        return new BookDto(
                fictionDto.getBookId(),
                fictionDto.getName(),
                fictionDto.getYear(),
                fictionDto.isTaken()
        );
        
    }
    
    /**
     * Mapira objekat klase FictionDto i objekat klase Book na Fiction
     *
     * @param fictionDto DTO koji se mapira kao instanca klase FictionDto
     * @param book Knjiga koja se mapira kao instanca klase Book
     * @return mapirani objekat kao instanca klase Fiction
     */
    public static Fiction mapToFiction(FictionDto fictionDto, Book book){
        
        
        return new Fiction(
                fictionDto.getBookId(),
                book,
                fictionDto.getGenre(),
                fictionDto.getTheme(),
                fictionDto.getWonPrizes()
                
        );
        
    }
    
    
    /**
     * Mapira objekat klase Fiction, objekat klase Book i listu autora na FictionDto
     *
     * @param fiction Beletrisika koja se mapira kao instanca klase Fiction
     * @param book Knjiga koja se mapira kao instanca klase Book
     * @param authors Lista autora koja se mapira
     * @return mapirani objekat kao instanca klase FictionDto
     */
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
