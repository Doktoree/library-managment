/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.dto;

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
public class LoanDtoTest {
    
    private LoanDto loanDto;
    
    @Test
    public void testLoanDto() {
        loanDto = new LoanDto();
        
        assertNotNull(loanDto);
        assertNull(loanDto.getLoanId());
        assertNotNull(loanDto.getStartDateOfLoan());
        assertNotNull(loanDto.getEndDateOfLoan());
        assertNull(loanDto.getMemberId());
        assertNull(loanDto.getLoanItems());
    }
    
    @Test
    public void testLoanDtoWithParameters() {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusDays(20);
        List<LoanItemDto> loanItems = new ArrayList<>();
        
        loanDto = new LoanDto(1L, startDate, endDate, 2L, loanItems);
        
        assertNotNull(loanDto);
        assertEquals(1L, loanDto.getLoanId());
        assertEquals(startDate, loanDto.getStartDateOfLoan());
        assertEquals(endDate, loanDto.getEndDateOfLoan());
        assertEquals(2L, loanDto.getMemberId());
        assertEquals(loanItems, loanDto.getLoanItems());
    }
    
    @Test
    public void testStartDateOfLoanDefaultValue() {
        loanDto = new LoanDto();
        
        assertNotNull(loanDto.getStartDateOfLoan());
    }
    
    @Test
    public void testEndDateOfLoanDefaultValue() {
        loanDto = new LoanDto();
        
        LocalDateTime expectedEndDate = LocalDateTime.now().plusDays(20);
        
        assertEquals(expectedEndDate, loanDto.getEndDateOfLoan());
    }
    
    @Test
    public void testLoanDtoToString() {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusDays(20);
        List<LoanItemDto> loanItems = new ArrayList<>();
        
        loanDto = new LoanDto(1L, startDate, endDate, 2L, loanItems);
        
        String expectedToString = "LoanDto(loanId=1, startDateOfLoan=" + startDate + ", endDateOfLoan=" + endDate + ", memberId=2, loanItems=" + loanItems + ")";
        
        assertEquals(expectedToString, loanDto.toString());
    }
}
