/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.ids;

import com.lav.library.domain.Loan;
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
public class LoanItemIdTest {
    
    @Test
    public void testLoanItemId() {
        LoanItemId loanItemId = new LoanItemId();
        
        assertNull(loanItemId.getLoanItemId());
        assertNull(loanItemId.getLoan());
    }

    @Test
    public void testConstructor() {
        Long loanItemIdValue = 1L;
        Loan loan = new Loan();
        LoanItemId loanItemId = new LoanItemId();

        loanItemId.setLoanItemId(loanItemIdValue);
        loanItemId.setLoan(loan);

        assertEquals(loanItemIdValue, loanItemId.getLoanItemId());
        assertEquals(loan, loanItemId.getLoan());
    }

    @Test
    public void testEquals() {
        Long loanItemIdValue1 = 1L;
        Long loanItemIdValue2 = 2L;
        Loan loan1 = new Loan();
        Loan loan2 = new Loan();

        LoanItemId loanItemId1 = new LoanItemId(loanItemIdValue1, loan1);
        LoanItemId loanItemId2 = new LoanItemId(loanItemIdValue1, loan1);
        LoanItemId loanItemId3 = new LoanItemId(loanItemIdValue2, loan2);

        assertEquals(loanItemId1, loanItemId2);
        assertNotEquals(loanItemId1, loanItemId3);
    }


    @Test
    public void testSetLoanItemId() {
        Long loanItemIdValue = 1L;
        LoanItemId loanItemId = new LoanItemId();

        loanItemId.setLoanItemId(loanItemIdValue);
        assertEquals(loanItemIdValue, loanItemId.getLoanItemId());
    }

    @Test
    public void testSetLoan() {
        Loan loan = new Loan();
        LoanItemId loanItemId = new LoanItemId();

        loanItemId.setLoan(loan);
        assertEquals(loan, loanItemId.getLoan());
    }
    
}
