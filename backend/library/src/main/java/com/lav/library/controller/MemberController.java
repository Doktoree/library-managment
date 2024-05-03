/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.controller;

import com.lav.library.dto.MemberDto;
import com.lav.library.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Lav
 */
@RestController
@RequestMapping("/api/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping
    public ResponseEntity<?> createMember(@RequestBody MemberDto memberDto) {

        if(memberDto.getFirstName() == null || memberDto.getLastName() == null || memberDto.getBirthDate() == null 
              || memberDto.getAdress() == null || memberDto.getPhoneNumber() == null){
            
            return ResponseEntity.badRequest().body("All fields are required!");
        }
        
        if(!(memberDto.getFirstName() instanceof String) || memberDto.getFirstName().isEmpty()){
            
            return ResponseEntity.badRequest().body("First name should not be empty!");
            
        }
        
        MemberDto createdMemberDto = memberService.createMember(memberDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdMemberDto);

    }

}
