/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.service;

import com.lav.library.domain.Member;
import com.lav.library.dto.MemberDto;
import com.lav.library.mapper.MemberMapper;
import com.lav.library.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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
    
    public MemberDto saveMember(MemberDto memberDto, Long id){
        
        Optional<Member> optionalMember = memberRepository.findById(id);
        
        if(optionalMember.isPresent()){
            
            Member updatedMember = optionalMember.get();
            updatedMember.setFirstName(memberDto.getFirstName());
            updatedMember.setLastName(memberDto.getLastName());
            updatedMember.setAdress(memberDto.getAdress());
            updatedMember.setPhoneNumber(memberDto.getPhoneNumber());
            updatedMember.setBirthDate(memberDto.getBirthDate());
            
            Member savedMember = memberRepository.save(updatedMember);
            return MemberMapper.mapToMemberDto(savedMember);
        }
        
        return null;
        
    }
    
    public List<MemberDto> getMembers(MemberDto memberDto){
        
        Member member = MemberMapper.mapToMember(memberDto);
        List<Member> members = memberRepository.findAll(Example.of(member));
        List<MemberDto> memberDtos = members.stream().map(MemberMapper::mapToMemberDto).collect(Collectors.toList());
        return memberDtos;
    }
    
    
}
