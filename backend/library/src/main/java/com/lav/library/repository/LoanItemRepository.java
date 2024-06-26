/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lav.library.repository;

import com.lav.library.domain.BookAuthor;
import com.lav.library.domain.Loan;
import com.lav.library.domain.LoanItem;
import com.lav.library.ids.LoanItemId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lav
 */
@Repository
public interface LoanItemRepository extends JpaRepository<LoanItem, LoanItemId> {

    List<LoanItem> findByLoan(Loan loan);

}
