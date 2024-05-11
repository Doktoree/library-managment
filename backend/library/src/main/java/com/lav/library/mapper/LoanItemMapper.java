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
 *
 * @author Lav
 */
public class LoanItemMapper {

    public static LoanItemDto mapToLoanDto(LoanItem loanItem) {

        return new LoanItemDto(loanItem.getLoanItemId(),
                loanItem.getLoan().getLoanId(),
                loanItem.getStatus(),
                loanItem.getBook().getBookId());

    }

    public static LoanItem mapToLoanItem(LoanItemDto dto) {

        Loan loan = new Loan();
        loan.setLoanId(dto.getLoanId());
        Book book = new Book();
        book.setBookId(dto.getBookId());
        return new LoanItem(dto.getLoanItemId(), loan, dto.getStatus(), book);

    }

}
