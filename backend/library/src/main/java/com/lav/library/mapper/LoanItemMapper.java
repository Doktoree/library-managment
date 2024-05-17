/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.mapper;

import com.lav.library.domain.Book;
import com.lav.library.domain.Loan;
import com.lav.library.domain.LoanItem;
import com.lav.library.dto.LoanItemDto;

/**
 * Klasa koja se koristi za mapiranje izmedju objekata klase LoanItem i LoanItemDto
 * 
 * @author Lav Jovanovic
 */
public class LoanItemMapper {

    /**
     * Mapira objekat klase LoanItem na LoanItemDto
     * 
     * @param loanItem Stavka zaduzenja koja se mapira kao instanca klase LoanItem
     * @return mapirani objekat kao instanca klase LoanItemDto
     */
    public static LoanItemDto mapToLoanDto(LoanItem loanItem) {

        return new LoanItemDto(loanItem.getLoanItemId(),
                loanItem.getLoan().getLoanId(),
                loanItem.getStatus(),
                loanItem.getBook().getBookId());

    }

    /**
     * Mapira objekat klase LoanItemDto na LoanItem
     * 
     * @param dto DTO koji se mapira kao instanca klase LoanItemDto
     * @return mapirani objekat kao instanca klase LoanItem
     */
    public static LoanItem mapToLoanItem(LoanItemDto dto) {

        Loan loan = new Loan();
        loan.setLoanId(dto.getLoanId());
        Book book = new Book();
        book.setBookId(dto.getBookId());
        return new LoanItem(dto.getLoanItemId(), loan, dto.getStatus(), book);

    }

}
