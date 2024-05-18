/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.mapper;

import com.lav.library.domain.Book;
import com.lav.library.domain.Loan;
import com.lav.library.domain.LoanItem;
import com.lav.library.dto.LoanItemDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Lav
 */
public class LoanItemMapperTest {

    @Test
    public void testMapToLoanDto() {
        Loan loan = new Loan();
        loan.setLoanId(1L);

        Book book = new Book();
        book.setBookId(2L);

        LoanItem loanItem = new LoanItem();
        loanItem.setLoanItemId(3L);
        loanItem.setLoan(loan);
        loanItem.setStatus("not returned");
        loanItem.setBook(book);

        LoanItemDto loanItemDto = LoanItemMapper.mapToLoanDto(loanItem);

        assertNotNull(loanItemDto);
        assertEquals(loanItem.getLoanItemId(), loanItemDto.getLoanItemId());
        assertEquals(loan.getLoanId(), loanItemDto.getLoanId());
        assertEquals(loanItem.getStatus(), loanItemDto.getStatus());
        assertEquals(book.getBookId(), loanItemDto.getBookId());
    }

    @Test
    public void testMapToLoanItem() {
        LoanItemDto loanItemDto = new LoanItemDto(3L, 1L, "not returned", 2L);

        LoanItem loanItem = LoanItemMapper.mapToLoanItem(loanItemDto);

        assertNotNull(loanItem);
        assertEquals(loanItemDto.getLoanItemId(), loanItem.getLoanItemId());
        assertEquals(loanItemDto.getLoanId(), loanItem.getLoan().getLoanId());
        assertEquals(loanItemDto.getStatus(), loanItem.getStatus());
        assertEquals(loanItemDto.getBookId(), loanItem.getBook().getBookId());
    }
}
