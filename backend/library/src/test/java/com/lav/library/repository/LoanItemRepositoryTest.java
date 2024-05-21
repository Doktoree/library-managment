/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.repository;

import com.lav.library.domain.Book;
import com.lav.library.domain.Loan;
import com.lav.library.domain.LoanItem;
import com.lav.library.domain.Member;
import com.lav.library.ids.LoanItemId;
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
public class LoanItemRepositoryTest {
    
    @Autowired
    private LoanItemRepository loanItemRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    private LoanItem loanItem;
    private Loan loan;
    private Book book;
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

        book = new Book();
        book.setName("Test Book");
        book.setYear(2021);
        book.setTaken(false);
        book = bookRepository.save(book);

        loan = new Loan();
        loan.setStartDateOfLoan(LocalDateTime.of(2023, 1, 1, 10, 0));
        loan.setEndDateOfLoan(LocalDateTime.of(2023, 1, 15, 10, 0));
        loan.setMember(member);
        loan = loanRepository.save(loan);

        loanItem = new LoanItem();
        loanItem.setLoan(loan);
        loanItem.setBook(book);
        loanItem.setStatus("not returned");
    }

    @AfterEach
    public void tearDown() {
        loanItemRepository.deleteAll();
        loanRepository.deleteAll();
        bookRepository.deleteAll();
        memberRepository.deleteAll();
        loanItem = null;
        loan = null;
        book = null;
        member = null;
    }

    @Test
    public void saveLoanItemTest() {
        LoanItem savedLoanItem = loanItemRepository.save(loanItem);
        
        assertNotNull(savedLoanItem);
        assertEquals(loanItem.getLoan().getLoanId(), savedLoanItem.getLoan().getLoanId());
        assertEquals(loanItem.getBook().getBookId(), savedLoanItem.getBook().getBookId());
        assertEquals(loanItem.getStatus(), savedLoanItem.getStatus());
    }

    @Test
    public void getAllLoanItemsTest() {
        loanItemRepository.save(loanItem);
        List<LoanItem> loanItems = loanItemRepository.findAll();
        assertFalse(loanItems.isEmpty());
        assertEquals(1, loanItems.size());
    }

    @Test
    public void findLoanItemByIdTest() {
        LoanItem savedLoanItem = loanItemRepository.save(loanItem);
        LoanItemId loanItemId = new LoanItemId(savedLoanItem.getLoanItemId(), savedLoanItem.getLoan());
        Optional<LoanItem> foundLoanItem = loanItemRepository.findById(loanItemId);
        assertTrue(foundLoanItem.isPresent());
        assertEquals(savedLoanItem.getLoanItemId(), foundLoanItem.get().getLoanItemId());
    }

    @Test
    public void deleteLoanItemTest() {
        LoanItem savedLoanItem = loanItemRepository.save(loanItem);
        LoanItemId loanItemId = new LoanItemId(savedLoanItem.getLoanItemId(), savedLoanItem.getLoan());
        loanItemRepository.delete(savedLoanItem);
        Optional<LoanItem> deletedLoanItem = loanItemRepository.findById(loanItemId);
        assertFalse(deletedLoanItem.isPresent());
    }

    @Test
    public void findLoanItemsByLoanTest() {
        LoanItem savedLoanItem = loanItemRepository.save(loanItem);
        List<LoanItem> loanItems = loanItemRepository.findByLoan(loan);
        assertFalse(loanItems.isEmpty());
        assertEquals(1, loanItems.size());
        assertEquals(savedLoanItem.getLoanItemId(), loanItems.get(0).getLoanItemId());
    }
}
