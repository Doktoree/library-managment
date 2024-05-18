/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lav.library.repository;

import com.lav.library.domain.Loan;
import com.lav.library.domain.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Lav Jovanovic
 */
public interface LoanRepository extends JpaRepository<Loan, Long> {
    
    List<Loan> findByMember(Member member);
    
}
