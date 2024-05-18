/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.domain;

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
public class LoanItemTest {

    private LoanItem loanItem;

    @BeforeEach
    public void setUp() {
        loanItem = new LoanItem();
    }

    @AfterEach
    public void tearDown() {
        loanItem = null;
    }

    @Test
    public void testLoanItem() {
        assertNotNull(loanItem);
        assertNull(loanItem.getLoanItemId());
        assertNull(loanItem.getLoan());
        assertEquals("not returned", loanItem.getStatus());
        assertNull(loanItem.getBook());
    }

    @Test
    public void testLoanItemConstructor() {
        Long loanItemId = 1L;
        Loan loan = new Loan();
        Book book = new Book();
        String status = "returned";

        LoanItem loanItem = new LoanItem(loanItemId, loan, status, book);

        assertEquals(loanItemId, loanItem.getLoanItemId());
        assertEquals(loan, loanItem.getLoan());
        assertEquals(status, loanItem.getStatus());
        assertEquals(book, loanItem.getBook());
    }

    @Test
    public void testSetAndGetLoanItemId() {
        Long loanItemId = 1L;
        loanItem.setLoanItemId(loanItemId);
        assertEquals(loanItemId, loanItem.getLoanItemId());
    }

    @Test
    public void testSetAndGetLoan() {
        Loan loan = new Loan();
        loanItem.setLoan(loan);
        assertEquals(loan, loanItem.getLoan());
    }

    @Test
    public void testSetAndGetStatus() {
        String status = "returned";
        loanItem.setStatus(status);
        assertEquals(status, loanItem.getStatus());
    }

    @Test
    public void testSetAndGetBook() {
        Book book = new Book();
        loanItem.setBook(book);
        assertEquals(book, loanItem.getBook());
    }

}
