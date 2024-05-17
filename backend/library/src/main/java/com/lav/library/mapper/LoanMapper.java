/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.mapper;

import com.lav.library.domain.Loan;
import com.lav.library.domain.LoanItem;
import com.lav.library.domain.Member;
import com.lav.library.dto.LoanDto;
import com.lav.library.dto.LoanItemDto;
import com.lav.library.dto.MemberDto;
import java.util.List;

/**
 * Klasa koja se koristi za mapiranje izmedju objekata klasa Loan, LoanDto i Member
 * 
 * @author Lav Jovanovic
 */
public class LoanMapper {
    
    /**
     * Mapira objekat klase Loan, objekat klase Member i listu LoanItemDto na LoanDto
     * 
     * @param loan Zaduzenje koje se mapira kao instanca klase Loan
     * @param member Clan koji se mapira kao instanca klase Member 
     * @param loanItems Lista LoanDto koji se mapiraju
     * @return mapirani objekat kao instanca klase LoanDto
     */
    public static LoanDto mapToLoanDto(Loan loan, Member member, List<LoanItemDto> loanItems){
        
        return new LoanDto(
                
                loan.getLoanId(),
                loan.getStartDateOfLoan(),
                loan.getEndDateOfLoan(),
                member.getMemberId(),
                loanItems
        
        );
        
    }
    
    /**
     * Mapira objekat klase LoanDto i objekat klase Member na Loan
     * 
     * @param dto DTO koji se mapira kao instanca klase LoanDto
     * @param member Clan koji se mapira kao instanca klase Member
     * @return mapirani objekat kao instanca klase Loan
     */
    public static Loan mapToLoan(LoanDto dto, Member member){
        
        return new Loan(
        
                dto.getLoanId(),
                dto.getStartDateOfLoan(),
                dto.getEndDateOfLoan(),
                member
        
        );
        
        
    }
    
    
    
}
