/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.controller;

import com.lav.library.domain.Member;
import com.lav.library.dto.LoanDto;
import com.lav.library.dto.MemberDto;
import com.lav.library.service.LoanService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Lav
 */
@RestController
@RequestMapping("/api/loan")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping
    public ResponseEntity<?> createLoan(@RequestBody LoanDto loanDto) {

        String validateMessage = validateLoanDto(loanDto);

        if (validateMessage != null) {
            return ResponseEntity.badRequest().body(validateMessage);
        }

        LoanDto createdLoanDto = loanService.createLoan(loanDto);

        if (createdLoanDto == null) {
            return ResponseEntity.badRequest().body("There is no member or book with the given ID!");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(createdLoanDto);

    }

    private String validateLoanDto(LoanDto loanDto) {

        if (loanDto.getLoanItems() == null || loanDto.getEndDateOfLoan() == null || loanDto.getMemberId() == null
                || loanDto.getStartDateOfLoan() == null) {
            return "All fields are required!";
        }

        if (loanDto.getLoanItems().size() == 0) {
            return "There must be at least one loan item!";
        }

        if (loanDto.getStartDateOfLoan().isAfter(LocalDateTime.now())) {
            return "Date must be a valid date in the past!";
        }

        if (loanDto.getStartDateOfLoan().isAfter(loanDto.getEndDateOfLoan())) {
            return "Start date of loan can not be after end date of loan!";
        }

        return null;
    }
    
    @GetMapping("{id}")
    public ResponseEntity<?> getLoan(@PathVariable Long id){
        
        LoanDto loanDto = loanService.getLoan(id);
        
        if(loanDto == null)
            return ResponseEntity.badRequest().body("There is no loan with the given ID!");
        
        return ResponseEntity.ok(loanDto);
        
    }
    
    @GetMapping
    public ResponseEntity<?> getLoan(@RequestBody LoanDto loanDto){
        
        Member member = new Member();
        member.setMemberId(loanDto.getMemberId());
        List<LoanDto> dtos = loanService.getLoan(member);
        
        if(dtos == null)
            return ResponseEntity.badRequest().body("There is no member with the given ID!");
        
        return ResponseEntity.ok(dtos);
        
    }
    
    @PatchMapping("{id}")
    public ResponseEntity<?> saveLoan(@RequestBody LoanDto loanDto, @PathVariable Long id){
        
        loanDto.setLoanId(id);
        System.out.println("Loan id: " + loanDto.getLoanId() + " id: " + id);
        LoanDto dto = loanService.saveLoan(loanDto);
        
        
        if(dto == null)
            return ResponseEntity.badRequest().body("One of ids are bad!");
        
        return ResponseEntity.ok(dto);
        
    }

}
