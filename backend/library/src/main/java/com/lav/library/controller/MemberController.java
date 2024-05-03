/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.controller;

import com.lav.library.dto.MemberDto;
import com.lav.library.service.MemberService;
import java.time.LocalDate;
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

        String validationMessage = validateMemberDto(memberDto);
        
        if(validationMessage != null)
            return ResponseEntity.badRequest().body(validationMessage);

        MemberDto createdMemberDto = memberService.createMember(memberDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdMemberDto);

    }

    private String validateMemberDto(MemberDto memberDto) {
        
        if (memberDto.getFirstName() == null || memberDto.getLastName() == null || memberDto.getBirthDate() == null
                || memberDto.getAdress() == null || memberDto.getPhoneNumber() == null) {

            return "All fields are required!";
        }

        if (!(memberDto.getFirstName() instanceof String) || memberDto.getFirstName().isEmpty()) {

            return "First name should not be empty!";

        }

        if (!(memberDto.getLastName() instanceof String) || memberDto.getLastName().isEmpty()) {

            return "Last name should not be empty!";

        }

        if (!(memberDto.getBirthDate() instanceof LocalDate) || memberDto.getBirthDate().isAfter(LocalDate.now())) {

            return "Birth date must be a valid date in the past.";

        }

        if (!(memberDto.getAdress() instanceof String) || memberDto.getLastName().isEmpty()) {

            return "Adress should not be empty!";

        }

        if (!(memberDto.getPhoneNumber() instanceof String) || memberDto.getPhoneNumber().isEmpty()) {

            return "Phone number should not be empty!";

        }
        
        return null;
    }
    
    

}
