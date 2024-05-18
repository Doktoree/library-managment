/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.controller;

import com.lav.library.domain.Member;
import com.lav.library.dto.MemberDto;
import com.lav.library.mapper.MemberMapper;
import com.lav.library.service.MemberService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest kontroler koji upravlja HTTP zahtevima vezanim za clanove biblioteke
 * Pruza krajnje tacke za kreiranje, dobijanje, azuriranje i brisanje clanova
 * 
 * @author Lav Jovanovic
 */
@RestController
@RequestMapping("/api/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    /**
     * Kreira novog clana
     * 
     * @param memberDto DTO objekat clana
     * @return kreirani objekat ili poruka o gresci
     */
    @PostMapping
    public ResponseEntity<?> createMember(@RequestBody MemberDto memberDto) {

        String validationMessage = validateMemberDto(memberDto);

        if (validationMessage != null) {
            return ResponseEntity.badRequest().body(validationMessage);
        }

        MemberDto createdMemberDto = memberService.createMember(memberDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdMemberDto);

    }

    /**
     * Validira DTO objekat clana
     * 
     * @param memberDto DTO clana
     * @return poruka o gresci ili null ako je validacija uspešna
     */
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

            return "Birth date must be a valid date in the past!";

        }

        if (!(memberDto.getAdress() instanceof String) || memberDto.getLastName().isEmpty()) {

            return "Adress should not be empty!";

        }

        if (!(memberDto.getPhoneNumber() instanceof String) || memberDto.getPhoneNumber().isEmpty()) {

            return "Phone number should not be empty!";

        }

        return null;
    }

    /**
     * Azurira clana sa datim ID-om
     * 
     * @param id ID clana
     * @param memberDto DTO clana
     * @return azurirani objekat ili poruka o gresci
     */
    @PatchMapping("{id}")
    public ResponseEntity<?> saveMember(@PathVariable Long id, @RequestBody MemberDto memberDto) {

        String validationMessage = validateMemberDto(memberDto);

        if (validationMessage != null) {
            return ResponseEntity.badRequest().body(validationMessage);
        }

        MemberDto savedMemberDto = memberService.saveMember(memberDto, id);

        if (savedMemberDto == null) {
            return ResponseEntity.badRequest().body("There is no member with the given ID!");
        }

        return ResponseEntity.ok(savedMemberDto);
    }
    
    /**
     * Pretrazuje clanove po zadatim kriterijumima
     * 
     * @param memberDto DTO objekat za pretragu
     * @return lista clanova koji zadovoljavaju kriterijume ili poruka o gresci
     */
     @GetMapping("/search")
     public ResponseEntity<?> getMembers(@RequestBody MemberDto memberDto){
              
         List<MemberDto> memberDtos = memberService.getMembers(memberDto);
         
         if(memberDtos.isEmpty())
             return ResponseEntity.badRequest().body("There are no members matching the given criteria.");
         
         return ResponseEntity.ok(memberDtos);
         
    }
     
     /**
     * Vraca clana sa datim id-om
     * 
     * @param id id clana
     * @return objekat clana ili poruka o gresci
     */
     @GetMapping("{id}")
     public ResponseEntity<?> getMember(@PathVariable Long id){
         
         MemberDto memberDto = memberService.getMember(id);
         
         if(memberDto==null)
             return ResponseEntity.badRequest().body("There is no member with the given ID!");
         
         return ResponseEntity.ok(memberDto);
         
     }
     
     /**
     * Vraca sve clanove
     * 
     * @return lista svih clanova ili poruka o gresci
     */
     @GetMapping
     public ResponseEntity<?> getAllMembers(){
         
         List<MemberDto> memberDtos = memberService.getAllMembers();
         
         if(memberDtos == null)
             return ResponseEntity.badRequest().body("There are no members!");
         
         return ResponseEntity.ok(memberDtos);
         
     }
     
     /**
     * Brise clana sa datim id-om
     * 
     * @param id id clana
     * @return poruka o uspesnom brisanju ili gresci
     */
     @DeleteMapping("{id}")
     public ResponseEntity<String> deleteMember(@PathVariable Long id){
         
         if(!memberService.deleteMember(id))
             return ResponseEntity.badRequest().body("There is no member with the given ID!");
         
         return ResponseEntity.ok("Member is succesfully deleted!");
         
     }

}
