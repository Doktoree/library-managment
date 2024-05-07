/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.mapper;

import com.lav.library.domain.Book;
import com.lav.library.dto.BookDto;

/**
 *
 * @author Lav
 */
public class BookMapper {

    public static BookDto mapToBookDto(Book book) {

        return new BookDto(
                book.getBookId(),
                book.getName(),
                book.getYear(),
                book.isTaken());

    }

    public static Book mapToBook(BookDto bookDto) {

        return new Book(
                bookDto.getBookId(),
                bookDto.getName(),
                bookDto.getYear(),
                bookDto.isTaken()
        );

    }

}
