/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.mapper;

import com.lav.library.domain.*;
import com.lav.library.dto.MemberDto;

/**
 *
 * @author Lav
 */
public class MemberMapper {
    
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
    
    public static Member mapToMember(MemberDto memberDto){
        
        return new Member(
        
               memberDto.getMemberId(),
                memberDto.getFirstName(),
                memberDto.getLastName(),
                memberDto.getAdress(),
                memberDto.getPhoneNumber(),
                memberDto.getBirthDate() 
        
        );
        
        
    }
    
}
