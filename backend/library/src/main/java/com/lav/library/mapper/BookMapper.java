/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.mapper;

import com.lav.library.domain.Book;
import com.lav.library.dto.BookDto;

/**
 * Klasa koja se koristi za mapiranje izmedju objekata klase Book i BookDto
 * 
 * @author Lav Jovanovic
 */
public class BookMapper {

    /**
     * Mapira objekat klase Book na BookDto
     * 
     * @param book Knjiga koji se mapira kao instanca klase Book
     * @return mapirani objekat kao instanca klase BookDto
     */
    public static BookDto mapToBookDto(Book book) {

        return new BookDto(
                book.getBookId(),
                book.getName(),
                book.getYear(),
                book.isTaken());

    }

    
    /**
     * Mapira objekat klase BookDto na Book
     * 
     * @param bookDto DTO koji se mapira kao instanca klase BookDto
     * @return mapirani objekat kao instanca klase Book
     */
    public static Book mapToBook(BookDto bookDto) {

        return new Book(
                bookDto.getBookId(),
                bookDto.getName(),
                bookDto.getYear(),
                bookDto.isTaken()
        );

    }

}
