/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Lav
 */
public class LoanItemDtoTest {
    
    private Validator validator;
    private LoanItemDto loanItemDto;
    
    
    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    @Test
    public void testLoanItemDto() {
        loanItemDto = new LoanItemDto();
        
        assertNotNull(loanItemDto);
        assertNull(loanItemDto.getLoanItemId());
        assertNull(loanItemDto.getLoanId());
        assertEquals("not returned", loanItemDto.getStatus());
        assertNull(loanItemDto.getBookId());
    }
    
    @Test
    public void testLoanItemDtoWithParameters() {
        loanItemDto = new LoanItemDto(1L, 2L, "returned", 3L);
        
        assertNotNull(loanItemDto);
        assertEquals(1L, loanItemDto.getLoanItemId());
        assertEquals(2L, loanItemDto.getLoanId());
        assertEquals("returned", loanItemDto.getStatus());
        assertEquals(3L, loanItemDto.getBookId());
    }
    
    @Test
    public void testLoanItemDtoToString() {
        loanItemDto = new LoanItemDto(1L, 2L, "returned", 3L);
        
        String expectedToString = "LoanItemDto(loanItemId=1, loanId=2, status=returned, bookId=3)";
        
        assertEquals(expectedToString, loanItemDto.toString());
    }
    
    @Test
    public void testBookIdNull(){
        
        loanItemDto = new LoanItemDto();
        loanItemDto.setLoanId(1L);
        loanItemDto.setStatus("not returned");
        
        Set<ConstraintViolation<LoanItemDto>> violations = validator.validate(loanItemDto);

        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("bookId") && v.getMessage().equals("Book id is required!")));
    }
            
}
