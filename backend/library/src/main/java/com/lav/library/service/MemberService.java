/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.service;

import com.lav.library.domain.Member;
import com.lav.library.dto.MemberDto;
import com.lav.library.mapper.MemberMapper;
import com.lav.library.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lav
 */
@Service
public class MemberService {
    
    @Autowired
    private MemberRepository memberRepository;
    
    public MemberDto createMember(MemberDto memberDto){
        
        Member member = MemberMapper.mapToMember(memberDto);
        Member createdMember = memberRepository.save(member);
        
        return MemberMapper.mapToMemberDto(createdMember);
    }
    
    public MemberDto saveMember(MemberDto memberDto){
        
        
        return null;
        
    }
    
    
}
