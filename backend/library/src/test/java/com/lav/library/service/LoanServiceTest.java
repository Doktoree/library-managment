/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.service;

import com.lav.library.domain.Book;
import com.lav.library.domain.Loan;
import com.lav.library.domain.LoanItem;
import com.lav.library.domain.Member;
import com.lav.library.dto.LoanDto;
import com.lav.library.dto.LoanItemDto;
import com.lav.library.mapper.LoanItemMapper;
import com.lav.library.repository.BookRepository;
import com.lav.library.repository.LoanItemRepository;
import com.lav.library.repository.LoanRepository;
import com.lav.library.repository.MemberRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author Lav Jovanovic
 */
@ExtendWith(MockitoExtension.class)
public class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private LoanItemRepository loanItemRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private LoanService loanService;

    private LoanDto loanDto;
    private Member member;
    private Book book;
    private Loan loan;
    private LoanItem loanItem;

    @BeforeEach
    public void setup() {
        loanDto = new LoanDto();
        loanDto.setLoanId(1L);
        loanDto.setMemberId(1L);
        loanDto.setStartDateOfLoan(LocalDateTime.now());
        loanDto.setEndDateOfLoan(LocalDateTime.now().plusDays(14));
        List<LoanItem> loanItems = new ArrayList<>();

        member = new Member();
        member.setMemberId(1L);

        book = new Book();
        book.setBookId(1L);
        book.setName("Test Book");
        book.setYear(2023);
        book.setTaken(false);

        loan = new Loan();
        loan.setLoanId(1L);
        loan.setMember(member);

        loanItem = new LoanItem();
        loanItem.setLoanItemId(1L);
        loanItem.setBook(book);
        loanItem.setStatus("not returned");
        loanItem.setLoan(loan);

        loanItems.add(loanItem);
        List<LoanItemDto> loanItemDtos = loanItems.stream().map(LoanItemMapper::mapToLoanDto).collect(Collectors.toList());
        loanDto.setLoanItems(loanItemDtos);

    }

    @Test
    public void createLoanTest() {
        given(memberRepository.findById(loanDto.getMemberId())).willReturn(Optional.of(member));
        given(loanRepository.save(any(Loan.class))).willReturn(loan);
        given(bookRepository.findById(loanItem.getBook().getBookId())).willReturn(Optional.of(book));
        given(loanItemRepository.save(any(LoanItem.class))).willReturn(loanItem);

        LoanDto createdLoanDto = loanService.createLoan(loanDto);

        assertThat(createdLoanDto).isNotNull();
        assertThat(createdLoanDto.getMemberId()).isEqualTo(loanDto.getMemberId());
        assertThat(createdLoanDto.getLoanItems()).hasSize(1);
    }

    @Test
    public void getLoanIDTest() {
        given(loanRepository.findById(1L)).willReturn(Optional.of(loan));
        given(loanItemRepository.findByLoan(loan)).willReturn(List.of(loanItem));

        LoanDto retrievedLoanDto = loanService.getLoan(1L);

        assertThat(retrievedLoanDto).isNotNull();
        assertThat(retrievedLoanDto.getMemberId()).isEqualTo(loanDto.getMemberId());
        assertThat(retrievedLoanDto.getLoanItems()).hasSize(1);
    }

    @Test
    public void getLoanMemberTest() {
        given(memberRepository.findById(member.getMemberId())).willReturn(Optional.of(member));
        given(loanRepository.findByMember(member)).willReturn(List.of(loan));
        given(loanItemRepository.findByLoan(loan)).willReturn(List.of(loanItem));

        List<LoanDto> retrievedLoanDtos = loanService.getLoan(member);

        assertThat(retrievedLoanDtos).isNotNull();
        assertThat(retrievedLoanDtos).hasSize(1);
        assertThat(retrievedLoanDtos.get(0).getMemberId()).isEqualTo(loanDto.getMemberId());
        assertThat(retrievedLoanDtos.get(0).getLoanItems()).hasSize(1);
    }

    @Test
    public void saveLoanTest() {
        given(loanRepository.findById(1L)).willReturn(Optional.of(loan));
        given(bookRepository.findById(loanItem.getBook().getBookId())).willReturn(Optional.of(book));
        given(loanItemRepository.findByLoan(loan)).willReturn(List.of(loanItem));
        given(bookRepository.save(any(Book.class))).willReturn(book);
        given(loanItemRepository.save(any(LoanItem.class))).willReturn(loanItem);

        LoanDto updatedLoanDto = loanService.saveLoan(loanDto);

        assertThat(updatedLoanDto).isNotNull();
        assertThat(updatedLoanDto.getMemberId()).isEqualTo(loanDto.getMemberId());
        assertThat(updatedLoanDto.getLoanItems()).hasSize(1);
    }

}
