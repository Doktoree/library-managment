/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.repository;

import com.lav.library.domain.Member;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author Lav Jovanovic
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MemberRepositoryTest {
    
    @Autowired
    private MemberRepository memberRepository;

    private Member member;

    @BeforeEach
    public void setUp() {
        member = new Member();
        member.setFirstName("John");
        member.setLastName("Doe");
        member.setAdress("123 Main St");
        member.setPhoneNumber("123-456-7890");
        member.setBirthDate(LocalDate.of(1990, 1, 1));
    }

    @AfterEach
    public void tearDown() {
        memberRepository.deleteAll();
        member = null;
    }

    @Test
    public void saveMemberTest() {
        Member savedMember = memberRepository.save(member);
        assertNotNull(savedMember);
        assertEquals(member.getFirstName(), savedMember.getFirstName());
        assertEquals(member.getLastName(), savedMember.getLastName());
        assertEquals(member.getAdress(), savedMember.getAdress());
        assertEquals(member.getPhoneNumber(), savedMember.getPhoneNumber());
        assertEquals(member.getBirthDate(), savedMember.getBirthDate());
    }

    @Test
    public void getAllMembersTest() {
        memberRepository.save(member);
        List<Member> members = memberRepository.findAll();
        assertFalse(members.isEmpty());
        assertEquals(1, members.size());
    }

    @Test
    public void findMemberByIdTest() {
        Member savedMember = memberRepository.save(member);
        Optional<Member> foundMember = memberRepository.findById(savedMember.getMemberId());
        assertTrue(foundMember.isPresent());
        assertEquals(savedMember.getMemberId(), foundMember.get().getMemberId());
    }

    @Test
    public void deleteMemberTest() {
        Member savedMember = memberRepository.save(member);
        memberRepository.delete(savedMember);
        Optional<Member> deletedMember = memberRepository.findById(savedMember.getMemberId());
        assertFalse(deletedMember.isPresent());
    }
    
}
