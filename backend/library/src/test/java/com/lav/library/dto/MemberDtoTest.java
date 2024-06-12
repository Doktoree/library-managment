/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Lav
 */
public class MemberDtoTest {

    private MemberDto memberDto;
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        memberDto = new MemberDto();
    }

    @AfterEach
    public void tearDown() {

        memberDto = null;
        validator = null;

    }

    @Test
    public void testMemberDto() {

        assertNotNull(memberDto);
        assertNull(memberDto.getMemberId());
        assertNull(memberDto.getFirstName());
        assertNull(memberDto.getLastName());
        assertNull(memberDto.getAdress());
        assertNull(memberDto.getPhoneNumber());
        assertNull(memberDto.getBirthDate());
    }

    @Test
    public void testMemberDtoWithParameters() {
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        memberDto = new MemberDto(1L, "Marko", "Markovic", "Adresa 123", "123456789", birthDate);

        assertNotNull(memberDto);
        assertEquals(1L, memberDto.getMemberId());
        assertEquals("Marko", memberDto.getFirstName());
        assertEquals("Markovic", memberDto.getLastName());
        assertEquals("Adresa 123", memberDto.getAdress());
        assertEquals("123456789", memberDto.getPhoneNumber());
        assertEquals(birthDate, memberDto.getBirthDate());
    }

    @Test
    public void testSetMemberId() {
        memberDto.setMemberId(1L);
        assertEquals(1L, memberDto.getMemberId());
    }

    @Test
    public void testSetFirstName() {
        memberDto.setFirstName("Marko");
        assertEquals("Marko", memberDto.getFirstName());
    }

    @Test
    public void testSetLastName() {
        memberDto.setLastName("Markovic");
        assertEquals("Markovic", memberDto.getLastName());
    }

    @Test
    public void testSetAdress() {
        memberDto.setAdress("Adresa 123");
        assertEquals("Adresa 123", memberDto.getAdress());
    }

    @Test
    public void testSetPhoneNumber() {
        memberDto.setPhoneNumber("123456789");
        assertEquals("123456789", memberDto.getPhoneNumber());
    }

    @Test
    public void testSetBirthDate() {
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        memberDto.setBirthDate(birthDate);
        assertEquals(birthDate, memberDto.getBirthDate());
    }

    @Test
    public void testInvalidFirstName() {
        memberDto = new MemberDto(null, "", "Markovic", "Ulica 1", "123456789", LocalDate.now().minusYears(30));

        Set<ConstraintViolation<MemberDto>> violations = validator.validate(memberDto);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("firstName") && v.getMessage().equals("First name is required!")));
    }

    @Test
    public void testInvalidLastName() {
        memberDto = new MemberDto(null, "Marko", "", "Ulica 1", "123456789", LocalDate.now().minusYears(30));

        Set<ConstraintViolation<MemberDto>> violations = validator.validate(memberDto);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("lastName") && v.getMessage().equals("Last name is required!")));
    }

    @Test
    public void testInvalidAddress() {
        memberDto = new MemberDto(null, "Marko", "Markovic", "", "123456789", LocalDate.now().minusYears(30));

        Set<ConstraintViolation<MemberDto>> violations = validator.validate(memberDto);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("adress") && v.getMessage().equals("Adress is required!")));
    }

    @Test
    public void testInvalidPhoneNumber() {
        memberDto = new MemberDto(null, "Marko", "Markovic", "Ulica 1", "", LocalDate.now().minusYears(30));

        Set<ConstraintViolation<MemberDto>> violations = validator.validate(memberDto);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("phoneNumber") && v.getMessage().equals("Phone number is required!")));
    }

    @Test
    public void testInvalidBirthDateInTheFuture() {
        memberDto = new MemberDto(null, "Marko", "Markovic", "Ulica 1", "123456789", LocalDate.now().plusDays(1));

        Set<ConstraintViolation<MemberDto>> violations = validator.validate(memberDto);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("birthDate") && v.getMessage().equals("Birth date must be a valid date in the past")));
    }

    @Test
    public void testFirstNameNull() {
        memberDto = new MemberDto(null, null, "Markovic", "Ulica 1", "123456789", LocalDate.now().minusYears(30));

        Set<ConstraintViolation<MemberDto>> violations = validator.validate(memberDto);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("firstName") && v.getMessage().equals("First name is required!")));
    }

    @Test
    public void testLastNameNull() {
        memberDto = new MemberDto(null, "Marko", null, "Ulica 1", "123456789", LocalDate.now().minusYears(30));

        Set<ConstraintViolation<MemberDto>> violations = validator.validate(memberDto);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("lastName") && v.getMessage().equals("Last name is required!")));
    }

    @Test
    public void testAddressNull() {
        memberDto = new MemberDto(1L, "Marko", "Markovic", null, "123456789", LocalDate.now().minusYears(30));

        Set<ConstraintViolation<MemberDto>> violations = validator.validate(memberDto);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("adress") && v.getMessage().equals("Adress is required!")));
    }

    @Test
    public void testPhoneNumberNull() {
        memberDto = new MemberDto(null, "Marko", "Markovic", "Ulica 1", null, LocalDate.now().minusYears(30));

        Set<ConstraintViolation<MemberDto>> violations = validator.validate(memberDto);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("phoneNumber") && v.getMessage().equals("Phone number is required!")));
    }
    
    @Test
    public void testBirthDateNull() {
        memberDto = new MemberDto(null, "Marko", "Markovic", "Ulica 1", "123456789", null);

        Set<ConstraintViolation<MemberDto>> violations = validator.validate(memberDto);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("birthDate") && v.getMessage().equals("Birth date is required")));
    }

}
