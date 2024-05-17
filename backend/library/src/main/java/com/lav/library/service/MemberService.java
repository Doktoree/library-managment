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
 * Predstavlja servisnu klasu za upravljanje clanovima u biblioteci
 * Clanovi se mogu kreirati, sacuvati i obrisati
 * 
 * @author Lav Jovanovic
 */
@Service
public class MemberService {
    
    @Autowired
    private MemberRepository memberRepository;
    
    /**
     * Kreira novog clana biblioteke na osnovu prosledjenih podataka
     *
     * @param memberDto podaci o clanu u DTO formatu
     * @return DTO koji predstavlja kreiranog clana
     */
    public MemberDto createMember(MemberDto memberDto){
        
        Member member = MemberMapper.mapToMember(memberDto);
        Member createdMember = memberRepository.save(member);
        
        return MemberMapper.mapToMemberDto(createdMember);
    }
    
     /**
     * Cuva azuriranu verziju clana biblioteke na osnovu prosledjenih podataka i id-ja
     *
     * @param memberDto novi podaci o clanu u DTO formatu
     * @param id id clana koji se azurira
     * @return azurirani DTO koji predstavlja clana biblioteke
     */
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
    
    /**
     * Pribavlja listu clanova biblioteke na osnovu prosledjenih podataka
     *
     * @param memberDto podaci o clanu na osnovu kojih se vrsi pretraga
     * @return Lista DTO-ova koji predstavljaju pronadjene clanove
     */
    public List<MemberDto> getMembers(MemberDto memberDto){
        
        Member member = MemberMapper.mapToMember(memberDto);
        List<Member> members = memberRepository.findAll(Example.of(member));
        List<MemberDto> memberDtos = members.stream().map(MemberMapper::mapToMemberDto).collect(Collectors.toList());
        return memberDtos;
    }
    
    /**
     * Pribavlja informacije o određenom clanu na osnovu ID-ja
     *
     * @param id id clana
     * @return DTO koji predstavlja informacije o clanu
     */
    public MemberDto getMember(Long id){
        
        Optional<Member> optionalMember = memberRepository.findById(id);
        
        if(optionalMember.isPresent()){
            
            Member member = optionalMember.get();
            MemberDto memberDto = MemberMapper.mapToMemberDto(member);
            return memberDto;
            
        }
        
        return null;
    }
    
    /**
     * Pribavlja sve clanove biblioteke
     *
     * @return Lista DTO-ova koji predstavljaju sve clanove biblioteke
     */
    public List<MemberDto> getAllMembers(){
        
        List<Member> allMembers = memberRepository.findAll();
        
        return allMembers.stream().map(MemberMapper::mapToMemberDto).collect(Collectors.toList());
    }
    
    /**
     * Brise clana biblioteke na osnovu id-ja
     *
     * @param id id clana koji se brise
     * @return true ako je clan uspesno obrisan, inace false
     */
    public boolean deleteMember(Long id){
        
        Optional<Member> optionalMember = memberRepository.findById(id);
        if(!optionalMember.isPresent())        
            return false;
            
        
        memberRepository.deleteById(id);
            
        return true;
        
        
    }
    
    
}
