/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.service;

import com.lav.library.domain.Book;
import com.lav.library.domain.Loan;
import com.lav.library.domain.LoanItem;
import com.lav.library.domain.Member;
import com.lav.library.dto.LoanDto;
import com.lav.library.dto.LoanItemDto;
import com.lav.library.ids.LoanItemId;
import com.lav.library.mapper.LoanItemMapper;
import com.lav.library.mapper.LoanMapper;
import com.lav.library.repository.BookRepository;
import com.lav.library.repository.LoanItemRepository;
import com.lav.library.repository.LoanRepository;
import com.lav.library.repository.MemberRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Predstavlja servisnu klasu za upravljanje zaduzenjima u biblioteci
 * Zaduzenja se mogu kreirati, ucitati i sacuvati
 * 
 * @author Lav Jovanovic
 */
@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LoanItemRepository loanItemRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BookRepository bookRepository;

    /**
     * Kreira novo zaduzenje na osnovu prosledjenih podataka
     *
     * @param dto podaci o zaduzenju u DTO formatu
     * @return DTO koji predstavlja kreirano zaduzenje
     */
    public LoanDto createLoan(LoanDto dto) {

        Optional<Member> optionalMember = memberRepository.findById(dto.getMemberId());

        if (!optionalMember.isPresent()) {
            return null;
        }

        Member member = optionalMember.get();

        Loan loan = LoanMapper.mapToLoan(dto, member);

        Loan savedLoan = loanRepository.save(loan);

        List<LoanItem> loanItems = dto.getLoanItems().stream().map(LoanItemMapper::mapToLoanItem).collect(Collectors.toList());
        List<LoanItem> savedLoanItems = new ArrayList<>();

        for (LoanItem li : loanItems) {
            Optional<Book> optionalBook = bookRepository.findById(li.getBook().getBookId());
            if (!optionalBook.isPresent()) {
                return null;
            }
            if (!optionalBook.get().isTaken()) {
                Book book = optionalBook.get();
                book.setTaken(true);
                bookRepository.save(book);
                li.setLoan(savedLoan);
                LoanItem savedLoanItem = loanItemRepository.save(li);
                savedLoanItems.add(savedLoanItem);
            }

        }

        return LoanMapper.mapToLoanDto(savedLoan, member, savedLoanItems.stream().map(LoanItemMapper::mapToLoanDto).collect(Collectors.toList()));
    }

    /**
     * Pribavlja informacije o odredjenoj pozajmici na osnovu id-ja.
     *
     * @param id id zaduzenja
     * @return DTO koji predstavlja informacije o zaduzenju
     */
    public LoanDto getLoan(Long id){
        
        Optional<Loan> optionalLoan = loanRepository.findById(id);
        
        if(!optionalLoan.isPresent())
            return null;
        
        Loan loan = optionalLoan.get();
        List<LoanItem> loanItems = loanItemRepository.findByLoan(loan);
        List<LoanItemDto> loanItemDtos = loanItems.stream().map(LoanItemMapper::mapToLoanDto).collect(Collectors.toList());
        return LoanMapper.mapToLoanDto(loan, loan.getMember(), loanItemDtos);
        
    }
    
     /**
     * Pribavlja listu zaduzenja za odredjenog clana
     *
     * @param member clan za koga se vrsi pretraga zaduzenja
     * @return lista DTO-ova koji predstavljaju zaduzenja clana
     */
    public List<LoanDto> getLoan(Member member){
        
        Optional<Member> optionalMember = memberRepository.findById(member.getMemberId());
        
        if(!optionalMember.isPresent())
            return null;
        
        Member newMember = optionalMember.get();
        
        List<Loan> loans = loanRepository.findByMember(member);
        
        List<LoanDto> loanDtos = new ArrayList<>();
        
        for(Loan l: loans){
            
            List<LoanItem> items = loanItemRepository.findByLoan(l);
            List<LoanItemDto> itemsDto = items.stream().map(LoanItemMapper::mapToLoanDto).collect(Collectors.toList());
            LoanDto dto = LoanMapper.mapToLoanDto(l, newMember,itemsDto );
            loanDtos.add(dto);
            
        }
        
        return loanDtos;
    }
    
    /**
     * Cuva azuriranu verziju zaduzenja
     *
     * @param dto novi podaci o zaduzenju
     * @return azurirani DTO koji predstavlja zaduzenje
     */
    public LoanDto saveLoan(LoanDto dto){
        
        Member member = new Member();
        member.setMemberId(dto.getMemberId());
        Optional<Loan> optionalLoan = loanRepository.findById(LoanMapper.mapToLoan(dto, member).getLoanId());
        List<LoanItemDto> dtos = new ArrayList<>();
        
        if(!optionalLoan.isPresent())
            return null;
        
        Loan loan = optionalLoan.get();
        
        
        List<LoanItem> loanItems = loanItemRepository.findByLoan(loan);
        
        for(int i = 0; i<dto.getLoanItems().size(); i++){
            
            for(int j = 0; j<loanItems.size(); j++){
                
                if(dto.getLoanItems().get(i).getLoanItemId() == loanItems.get(j).getLoanItemId()){
                    
                    LoanItem item = loanItems.get(j);
                    item.setStatus(dto.getLoanItems().get(i).getStatus());
                    LoanItem savedItem = loanItemRepository.save(item);
                    Long bookID = savedItem.getBook().getBookId();
                    Optional<Book> optionalBook = bookRepository.findById(bookID);
                    Book book = optionalBook.get();
                    book.setTaken(false);
                    Book savedBook = bookRepository.save(book);
                    LoanItemDto dto1 = LoanItemMapper.mapToLoanDto(savedItem);
                    dtos.add(dto1);
                    
                }
                
            }
            
        }
        
        LoanDto loanDto = LoanMapper.mapToLoanDto(loan, member, dtos);
        
        return loanDto;
    }
}
