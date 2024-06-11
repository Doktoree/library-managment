/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.controller;

import com.lav.library.domain.Member;
import com.lav.library.dto.LoanDto;
import com.lav.library.dto.MemberDto;
import com.lav.library.service.LoanService;
import jakarta.validation.Valid;
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
 * Rest kontroler koji upravlja HTTP zahtevima vezanim za zaduzenja knjiga
 * Pruza krajnje tacke za kreiranje, dobijanje i azuriranje zaduzenja
 * 
 * @author Lav Jovanovic
 */
@RestController
@RequestMapping("/api/loan")
public class LoanController {

    @Autowired
    private LoanService loanService;

    /**
     * Kreira novo zaduzenje
     * 
     * @param loanDto DTO objekat zaduzenja
     * @return kreirani objekat ili poruka o gresci
     */
    @PostMapping
    public ResponseEntity<?> createLoan(@Valid @RequestBody LoanDto loanDto) {

        LoanDto createdLoanDto = loanService.createLoan(loanDto);

        if (createdLoanDto == null) {
            return ResponseEntity.badRequest().body("There is no member or book with the given ID!");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(createdLoanDto);

    }

    /**
     * Vraca zaduzenje sa datim id-om
     * 
     * @param id ID zaduzenja
     * @return objekat zaduzenja ili poruka o gresci
     */
    @GetMapping("{id}")
    public ResponseEntity<?> getLoan(@PathVariable Long id) {

        LoanDto loanDto = loanService.getLoan(id);

        if (loanDto == null) {
            return ResponseEntity.badRequest().body("There is no loan with the given ID!");
        }

        return ResponseEntity.ok(loanDto);

    }

    /**
     * Pretrazuje zaduzenja po zadatim kriterijumima
     * 
     * @param loanDto DTO objekat za pretragu
     * @return lista zaduzenja koje zadovoljavaju kriterijume ili poruka o gresci
     */
    @GetMapping
    public ResponseEntity<?> getLoan(@RequestBody LoanDto loanDto) {

        Member member = new Member();
        member.setMemberId(loanDto.getMemberId());
        List<LoanDto> dtos = loanService.getLoan(member);

        if (dtos == null) {
            return ResponseEntity.badRequest().body("There is no member with the given ID!");
        }

        return ResponseEntity.ok(dtos);

    }

    /**
     * Azurira zaduzenje sa datim id-om
     * 
     * @param loanDto DTO zaduzenja
     * @param id ID zaduzenja
     * @return azurirani objekat ili poruka o gresci
     */
    @PatchMapping("{id}")
    public ResponseEntity<?> saveLoan(@RequestBody LoanDto loanDto, @PathVariable Long id) {

        loanDto.setLoanId(id);
        System.out.println("Loan id: " + loanDto.getLoanId() + " id: " + id);
        LoanDto dto = loanService.saveLoan(loanDto);

        if (dto == null) {
            return ResponseEntity.badRequest().body("One of ids are bad!");
        }

        return ResponseEntity.ok(dto);

    }

}
