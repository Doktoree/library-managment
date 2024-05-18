/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.domain;

import java.time.LocalDateTime;
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
public class LoanTest {
    
    private Loan loan;

    @BeforeEach
    public void setUp() {
        loan = new Loan();
    }

    @AfterEach
    public void tearDown() {
        loan = null;
    }

    @Test
    public void testLoan() {
        assertNotNull(loan);
        assertNull(loan.getLoanId());
        assertNull(loan.getStartDateOfLoan());
        assertNull(loan.getEndDateOfLoan());
        assertNull(loan.getMember());
    }

    @Test
    public void testSetAndGetLoanId() {
        Long loanId = 1L;
        loan.setLoanId(loanId);
        assertEquals(loanId, loan.getLoanId());
    }

    @Test
    public void testSetAndGetStartDateOfLoan() {
        LocalDateTime startDateOfLoan = LocalDateTime.now();
        loan.setStartDateOfLoan(startDateOfLoan);
        assertEquals(startDateOfLoan, loan.getStartDateOfLoan());
    }

    @Test
    public void testSetAndGetEndDateOfLoan() {
        LocalDateTime endDateOfLoan = LocalDateTime.now().plusDays(7);
        loan.setEndDateOfLoan(endDateOfLoan);
        assertEquals(endDateOfLoan, loan.getEndDateOfLoan());
    }

    @Test
    public void testSetAndGetMember() {
        Member member = new Member();
        loan.setMember(member);
        assertEquals(member, loan.getMember());
    }
    
}
