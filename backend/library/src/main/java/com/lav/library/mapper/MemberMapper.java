/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.mapper;

import com.lav.library.domain.*;
import com.lav.library.dto.MemberDto;

/**
 * Klasa koja se koristi za mapiranje izmedju objekata klase Member i MemberDto
 * 
 * @author Lav Jovanovic
 */
public class MemberMapper {
    
    /**
     * Mapira objekat klase Member na MemberDto
     * 
     * @param member Clan koji se mapira kao instanca klase Member
     * @return mapirani objekat kao instanca klase MemberDto
     */
    public static MemberDto mapToMemberDto(Member member){
        
        return new MemberDto(
        
                member.getMemberId(),
                member.getFirstName(),
                member.getLastName(),
                member.getAdress(),
                member.getPhoneNumber(),
                member.getBirthDate()
        
        );
        
        
    }
    
    /**
     * Mapira objekat klase MemberDto na Member
     * 
     * @param memberDto DTO koji se mapira kao instanca klase MemberDto
     * @return mapirani objekat kao instanca klase Member
     */
    public static Member mapToMember(MemberDto memberDto){
        
        return new Member(
        
               memberDto.getMemberId(),
                memberDto.getFirstName(),
                memberDto.getLastName(),
                memberDto.getAdress(),
                memberDto.getPhoneNumber(),
                memberDto.getBirthDate(),
                null
                
        
        );
        
        
    }
    
}
