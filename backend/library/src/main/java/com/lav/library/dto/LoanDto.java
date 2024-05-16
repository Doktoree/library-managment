/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.dto;

import com.lav.library.domain.LoanItem;
import com.lav.library.domain.Member;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.internal.build.AllowSysOut;

/**
 *
 * @author Lav
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoanDto {
    
    private Long loanId;
    private LocalDateTime startDateOfLoan = LocalDateTime.now();
    private LocalDateTime endDateOfLoan = startDateOfLoan.plusDays(20);
    private Long memberId;
    private List<LoanItemDto> loanItems;
    
}
