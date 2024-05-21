/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.repository;

import com.lav.library.domain.Loan;
import com.lav.library.domain.Member;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author Lav Jovanovic
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LoanRepositoryTest {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private MemberRepository memberRepository;

    private Loan loan;
    private Member member;

    @BeforeEach
    public void setUp() {
        member = new Member();
        member.setFirstName("Marko");
        member.setLastName("Markovic");
        member.setAdress("Street 1");
        member.setBirthDate(LocalDate.now());
        member.setPhoneNumber("12345678");
        member = memberRepository.save(member);

        loan = new Loan();
        loan.setStartDateOfLoan(LocalDateTime.of(2023, 1, 1, 10, 0));
        loan.setEndDateOfLoan(LocalDateTime.of(2023, 1, 15, 10, 0));
        loan.setMember(member);
    }

    @AfterEach
    public void tearDown() {
        loanRepository.deleteAll();
        memberRepository.deleteAll();
        loan = null;
        member = null;
    }

    @Test
    public void saveLoanTest() {
        Loan savedLoan = loanRepository.save(loan);

        assertNotNull(savedLoan);
        assertEquals(loan.getLoanId(), savedLoan.getLoanId());
        assertEquals(loan.getStartDateOfLoan(), savedLoan.getStartDateOfLoan());
        assertEquals(loan.getEndDateOfLoan(), savedLoan.getEndDateOfLoan());
        assertEquals(loan.getMember().getMemberId(), savedLoan.getMember().getMemberId());
    }

    @Test
    public void getAllLoansTest() {
        loanRepository.save(loan);
        List<Loan> loans = loanRepository.findAll();
        assertFalse(loans.isEmpty());
        assertEquals(1, loans.size());
    }

    @Test
    public void findLoanByIdTest() {
        Loan savedLoan = loanRepository.save(loan);
        Optional<Loan> foundLoan = loanRepository.findById(savedLoan.getLoanId());
        assertTrue(foundLoan.isPresent());
        assertEquals(savedLoan.getLoanId(), foundLoan.get().getLoanId());
    }

    @Test
    public void deleteLoanTest() {
        Loan savedLoan = loanRepository.save(loan);
        loanRepository.delete(savedLoan);
        Optional<Loan> deletedLoan = loanRepository.findById(savedLoan.getLoanId());
        assertFalse(deletedLoan.isPresent());
    }

    @Test
    public void findLoansByMemberTest() {
        Loan savedLoan = loanRepository.save(loan);
        List<Loan> loans = loanRepository.findByMember(member);
        assertFalse(loans.isEmpty());
        assertEquals(1, loans.size());
        assertEquals(savedLoan.getLoanId(), loans.get(0).getLoanId());
    }

}
