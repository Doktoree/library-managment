/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.mapper;

import com.lav.library.domain.Loan;
import com.lav.library.domain.Member;
import com.lav.library.dto.LoanDto;
import com.lav.library.dto.LoanItemDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
public class LoanMapperTest {

    @Test
    public void testMapToLoanDto() {
        Loan loan = new Loan();
        loan.setLoanId(1L);
        loan.setStartDateOfLoan(LocalDateTime.now().minusDays(5));
        loan.setEndDateOfLoan(LocalDateTime.now().plusDays(15));

        Member member = new Member();
        member.setMemberId(1L);

        LoanItemDto loanItemDto1 = new LoanItemDto(1L, 1L, "not returned", 1L);
        LoanItemDto loanItemDto2 = new LoanItemDto(2L, 1L, "returned", 2L);
        List<LoanItemDto> loanItems = new ArrayList<>();
        loanItems.add(loanItemDto1);
        loanItems.add(loanItemDto2);

        LoanDto loanDto = LoanMapper.mapToLoanDto(loan, member, loanItems);

        assertNotNull(loanDto);
        assertEquals(loan.getLoanId(), loanDto.getLoanId());
        assertEquals(loan.getStartDateOfLoan(), loanDto.getStartDateOfLoan());
        assertEquals(loan.getEndDateOfLoan(), loanDto.getEndDateOfLoan());
        assertEquals(member.getMemberId(), loanDto.getMemberId());
        assertEquals(loanItems, loanDto.getLoanItems());
    }

    @Test
    public void testMapToLoan() {
        LoanDto loanDto = new LoanDto();
        loanDto.setLoanId(1L);
        loanDto.setStartDateOfLoan(LocalDateTime.now().minusDays(5));
        loanDto.setEndDateOfLoan(LocalDateTime.now().plusDays(15));

        Member member = new Member();
        member.setMemberId(1L);

        Loan loan = LoanMapper.mapToLoan(loanDto, member);

        assertNotNull(loan);
        assertEquals(loanDto.getLoanId(), loan.getLoanId());
        assertEquals(loanDto.getStartDateOfLoan(), loan.getStartDateOfLoan());
        assertEquals(loanDto.getEndDateOfLoan(), loan.getEndDateOfLoan());
        assertEquals(member, loan.getMember());
    }
}
