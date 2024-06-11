/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Lav
 */
public class LoanDtoTest {

    private LoanDto loanDto;
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

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

        LocalDateTime expectedEndDate = loanDto.getStartDateOfLoan().plusDays(20);

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

    @Test
    public void testMemberIdNotNull() {
        loanDto = new LoanDto();
        List<LoanItemDto> loanItems = new ArrayList<>();
        loanItems.add(new LoanItemDto());
        loanDto.setLoanItems(loanItems);

        Set<ConstraintViolation<LoanDto>> violations = validator.validate(loanDto);

        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("memberId") && v.getMessage().equals("Member ID is required")));
    }

    @Test
    public void testLoanItemsNotNull() {
        loanDto = new LoanDto();
        loanDto.setMemberId(1L);

        Set<ConstraintViolation<LoanDto>> violations = validator.validate(loanDto);

        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("loanItems") && v.getMessage().equals("Loan items are required")));
    }

    @Test
    public void testLoanItemsSize() {
        loanDto = new LoanDto();
        loanDto.setMemberId(1L);
        loanDto.setLoanItems(new ArrayList<>());

        Set<ConstraintViolation<LoanDto>> violations = validator.validate(loanDto);

        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("loanItems") && v.getMessage().equals("There must be at least one loan item")));
    }
}
