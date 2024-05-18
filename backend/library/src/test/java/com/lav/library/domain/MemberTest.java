/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
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
public class MemberTest {

    private Member member;

    @BeforeEach
    public void setUp() {
        member = new Member();
    }

    @AfterEach
    public void tearDown() {
        member = null;
    }

    @Test
    public void testMember() {
        assertNotNull(member);
        assertNull(member.getMemberId());
        assertNull(member.getFirstName());
        assertNull(member.getLastName());
        assertNull(member.getAdress());
        assertNull(member.getPhoneNumber());
        assertNull(member.getBirthDate());
        assertNull(member.getLoans());
    }

    @Test
    public void testConstructorWithParameters() {
        Long memberId = 1L;
        String firstName = "Marko";
        String lastName = "Marković";
        String address = "Adresa 123";
        String phoneNumber = "123456789";
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        Set<Loan> loans = new HashSet<>();

        Member member = new Member(memberId, firstName, lastName, address, phoneNumber, birthDate, loans);

        assertEquals(memberId, member.getMemberId());
        assertEquals(firstName, member.getFirstName());
        assertEquals(lastName, member.getLastName());
        assertEquals(address, member.getAdress());
        assertEquals(phoneNumber, member.getPhoneNumber());
        assertEquals(birthDate, member.getBirthDate());
        assertEquals(loans, member.getLoans());
    }

    @Test
    public void testSetAndGetMemberId() {
        Long memberId = 1L;
        member.setMemberId(memberId);
        assertEquals(memberId, member.getMemberId());
    }

    @Test
    public void testSetAndGetFirstName() {
        String firstName = "Marko";
        member.setFirstName(firstName);
        assertEquals(firstName, member.getFirstName());
    }

    @Test
    public void testSetAndGetLastName() {
        String lastName = "Marković";
        member.setLastName(lastName);
        assertEquals(lastName, member.getLastName());
    }

    @Test
    public void testSetAndGetAdress() {
        String address = "Adresa 123";
        member.setAdress(address);
        assertEquals(address, member.getAdress());
    }

    @Test
    public void testSetAndGetPhoneNumber() {
        String phoneNumber = "123456789";
        member.setPhoneNumber(phoneNumber);
        assertEquals(phoneNumber, member.getPhoneNumber());
    }

    @Test
    public void testSetAndGetBirthDate() {
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        member.setBirthDate(birthDate);
        assertEquals(birthDate, member.getBirthDate());
    }

    @Test
    public void testSetAndGetLoans() {
        Set<Loan> loans = new HashSet<>();
        member.setLoans(loans);
        assertEquals(loans, member.getLoans());
    }

    @Test
    public void testToString() {
        Long memberId = 1L;
        String firstName = "Marko";
        String lastName = "Marković";
        String address = "Adresa 123";
        String phoneNumber = "123456789";
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        Set<Loan> loans = new HashSet<>();

        Member member = new Member(memberId, firstName, lastName, address, phoneNumber, birthDate, loans);

        String expectedToString = "Member(memberId=1, firstName=Marko, lastName=Marković, adress=Adresa 123, phoneNumber=123456789, birthDate=1990-01-01, loans=[])";
        String actualToString = member.toString();

        assertEquals(expectedToString, actualToString);
    }

}
