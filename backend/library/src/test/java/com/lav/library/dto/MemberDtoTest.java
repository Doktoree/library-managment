/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.dto;

import java.time.LocalDate;
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
public class MemberDtoTest {
    
    private MemberDto memberDto;
    
    @Test
    public void testMemberDto() {
        memberDto = new MemberDto();
        
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
        memberDto = new MemberDto();
        memberDto.setMemberId(1L);
        assertEquals(1L, memberDto.getMemberId());
    }
    
    @Test
    public void testSetFirstName() {
        memberDto = new MemberDto();
        memberDto.setFirstName("Marko");
        assertEquals("Marko", memberDto.getFirstName());
    }
    
    @Test
    public void testSetLastName() {
        memberDto = new MemberDto();
        memberDto.setLastName("Markovic");
        assertEquals("Markovic", memberDto.getLastName());
    }
    
    @Test
    public void testSetAdress() {
        memberDto = new MemberDto();
        memberDto.setAdress("Adresa 123");
        assertEquals("Adresa 123", memberDto.getAdress());
    }
    
    @Test
    public void testSetPhoneNumber() {
        memberDto = new MemberDto();
        memberDto.setPhoneNumber("123456789");
        assertEquals("123456789", memberDto.getPhoneNumber());
    }
    
    @Test
    public void testSetBirthDate() {
        memberDto = new MemberDto();
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        memberDto.setBirthDate(birthDate);
        assertEquals(birthDate, memberDto.getBirthDate());
    }
    
}
