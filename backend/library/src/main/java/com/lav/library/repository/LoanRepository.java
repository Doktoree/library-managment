/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lav.library.repository;

import com.lav.library.domain.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Lav
 */
public interface LoanRepository extends JpaRepository<Loan, Long> {
    
    
    
}
