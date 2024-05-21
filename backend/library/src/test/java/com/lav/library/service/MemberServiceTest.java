/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.service;

import com.lav.library.domain.Member;
import com.lav.library.dto.MemberDto;
import com.lav.library.repository.MemberRepository;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author Lav Jovanovic
 */
@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {
    
   @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    private MemberDto memberDto;
    private Member member;

    @BeforeEach
    public void setup() {
        memberDto = new MemberDto();
        memberDto.setFirstName("John");
        memberDto.setLastName("Doe");
        memberDto.setAdress("123 Main St");
        memberDto.setPhoneNumber("555-1234");
        memberDto.setBirthDate(LocalDate.now());

        member = new Member();
        member.setMemberId(1L);
        member.setFirstName("John");
        member.setLastName("Doe");
        member.setAdress("123 Main St");
        member.setPhoneNumber("555-1234");
        member.setBirthDate(LocalDate.of(1999, Month.MARCH, 3));
    }

    @Test
    public void createMemberTest() {
        given(memberRepository.save(any(Member.class))).willReturn(member);

        MemberDto createdMemberDto = memberService.createMember(memberDto);

        assertThat(createdMemberDto).isNotNull();
        assertThat(createdMemberDto.getFirstName()).isEqualTo(memberDto.getFirstName());
        assertThat(createdMemberDto.getLastName()).isEqualTo(memberDto.getLastName());

        verify(memberRepository, times(1)).save(any(Member.class));
    }

    @Test
    public void saveMemberTest() {
        given(memberRepository.findById(1L)).willReturn(Optional.of(member));
        given(memberRepository.save(any(Member.class))).willReturn(member);

        MemberDto updatedMemberDto = memberService.saveMember(memberDto, 1L);

        assertThat(updatedMemberDto).isNotNull();
        assertThat(updatedMemberDto.getFirstName()).isEqualTo(memberDto.getFirstName());
        assertThat(updatedMemberDto.getLastName()).isEqualTo(memberDto.getLastName());

        verify(memberRepository, times(1)).save(any(Member.class));
    }

    @Test
    public void getMemberDTOTest() {
        given(memberRepository.findById(1L)).willReturn(Optional.of(member));

        MemberDto retrievedMemberDto = memberService.getMember(1L);

        assertThat(retrievedMemberDto).isNotNull();
        assertThat(retrievedMemberDto.getFirstName()).isEqualTo(member.getFirstName());
        assertThat(retrievedMemberDto.getLastName()).isEqualTo(member.getLastName());
    }
    
    @Test
    public void getMemberIDTest() {
        Long memberId = 1L;
        Member mockMember = new Member();
        mockMember.setMemberId(memberId);
        mockMember.setFirstName("John");
        mockMember.setLastName("Doe");
        given(memberRepository.findById(memberId)).willReturn(Optional.of(mockMember));
        MemberDto result = memberService.getMember(memberId);
        assertThat(result).isNotNull();
        assertThat(result.getMemberId()).isEqualTo(memberId);
        assertThat(result.getFirstName()).isEqualTo(mockMember.getFirstName());
        assertThat(result.getLastName()).isEqualTo(mockMember.getLastName());
    }

    @Test
    public void getMemberIDTestMember() {
        Long memberId = 1L;
        given(memberRepository.findById(memberId)).willReturn(Optional.empty());
        MemberDto result = memberService.getMember(memberId);
        assertThat(result).isNull();
    }

    @Test
    public void getAllMembersTest() {
        List<Member> mockMembers = new ArrayList<>();
        mockMembers.add(new Member());
        mockMembers.add(new Member());
        given(memberRepository.findAll()).willReturn(mockMembers);
        List<MemberDto> result = memberService.getAllMembers();
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(mockMembers.size());
    }

    @Test
    public void deleteMemberTest() {
        Long memberId = 1L;
        Member mockMember = new Member();
        mockMember.setMemberId(memberId);
        given(memberRepository.findById(memberId)).willReturn(Optional.of(mockMember));
        boolean result = memberService.deleteMember(memberId);
        assertThat(result).isTrue();
        verify(memberRepository, times(1)).deleteById(memberId);
    }

    @Test
    public void deleteMemberTestMember() {
        Long memberId = 1L;
        given(memberRepository.findById(memberId)).willReturn(Optional.empty());
        boolean result = memberService.deleteMember(memberId);
        assertThat(result).isFalse();
        verify(memberRepository, times(0)).deleteById(memberId);
    }
}
