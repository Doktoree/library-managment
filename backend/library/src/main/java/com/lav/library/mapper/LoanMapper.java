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
 *
 * @author Lav
 */
public class LoanMapper {
    
    public static LoanDto mapToLoanDto(Loan loan, Member member, List<LoanItemDto> loanItems){
        
        return new LoanDto(
                
                loan.getLoanId(),
                loan.getStartDateOfLoan(),
                loan.getEndDateOfLoan(),
                member.getMemberId(),
                loanItems
        
        );
        
    }
    
    
    public static Loan mapToLoan(LoanDto dto, Member member){
        
        return new Loan(
        
                dto.getLoanId(),
                dto.getStartDateOfLoan(),
                dto.getEndDateOfLoan(),
                member
        
        );
        
        
    }
    
    
    
}
