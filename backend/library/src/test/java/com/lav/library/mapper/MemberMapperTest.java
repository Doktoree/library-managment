/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.mapper;

import com.lav.library.domain.Member;
import com.lav.library.dto.MemberDto;
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
public class MemberMapperTest {
    
   @Test
    public void testMapToMemberDto() {
        Member member = new Member();
        member.setMemberId(1L);
        member.setFirstName("Marko");
        member.setLastName("Markovic");
        member.setAdress("Adresa 123");
        member.setPhoneNumber("123456789");
        member.setBirthDate(LocalDate.of(1990, 1, 1));
        
        MemberDto memberDto = MemberMapper.mapToMemberDto(member);
        
        assertNotNull(memberDto);
        assertEquals(member.getMemberId(), memberDto.getMemberId());
        assertEquals(member.getFirstName(), memberDto.getFirstName());
        assertEquals(member.getLastName(), memberDto.getLastName());
        assertEquals(member.getAdress(), memberDto.getAdress());
        assertEquals(member.getPhoneNumber(), memberDto.getPhoneNumber());
        assertEquals(member.getBirthDate(), memberDto.getBirthDate());
    }
    
    @Test
    public void testMapToMember() {
        MemberDto memberDto = new MemberDto();
        memberDto.setMemberId(1L);
        memberDto.setFirstName("Marko");
        memberDto.setLastName("Markovic");
        memberDto.setAdress("Adresa 123");
        memberDto.setPhoneNumber("123456789");
        memberDto.setBirthDate(LocalDate.of(1990, 1, 1));
        
        Member member = MemberMapper.mapToMember(memberDto);
        
        assertNotNull(member);
        assertEquals(memberDto.getMemberId(), member.getMemberId());
        assertEquals(memberDto.getFirstName(), member.getFirstName());
        assertEquals(memberDto.getLastName(), member.getLastName());
        assertEquals(memberDto.getAdress(), member.getAdress());
        assertEquals(memberDto.getPhoneNumber(), member.getPhoneNumber());
        assertEquals(memberDto.getBirthDate(), member.getBirthDate());
        assertNull(member.getLoans());
    }
}
