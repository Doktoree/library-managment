/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.controller;

import com.lav.library.domain.Author;
import com.lav.library.domain.Fiction;
import com.lav.library.dto.BookDto;
import com.lav.library.dto.FictionDto;
import com.lav.library.dto.MemberDto;
import com.lav.library.dto.ProfessionalLiteratureDto;
import com.lav.library.service.AuthorService;
import com.lav.library.service.BookService;
import com.lav.library.service.FictionService;
import com.lav.library.service.ProfessionalLiteratureService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

/**
 *
 * @author Lav
 */
@RestController
@RequestMapping("/api/book")
public class BookController {
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private ProfessionalLiteratureService plService;
    
    @Autowired
    private FictionService fictionService;
    
    @Autowired
    private AuthorService authorService;
    
    @GetMapping
    public ResponseEntity<?> getAllBooks(){
        
        List<BookDto> allBooks = bookService.getAllBooks();
        
        if(allBooks.isEmpty())
            return ResponseEntity.badRequest().body("There are no books");
        
        return ResponseEntity.ok(allBooks);
        
    }
    
    @PostMapping("/pliterature")
    public ResponseEntity<?> createBook(@RequestBody ProfessionalLiteratureDto literatureDto){
        
        String validateMessage = validate(literatureDto);
        
        if(validateMessage != null)
            return ResponseEntity.badRequest().body(validateMessage);
        
        ProfessionalLiteratureDto createPlDto = plService.createProfessionalLiterature(literatureDto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createPlDto);
    }
    
    @PostMapping("/fiction")
    public ResponseEntity<?> createBook(@RequestBody FictionDto fictionDto){
        
        String validateMessage = validate(fictionDto);
        
        if(validateMessage != null)
            return ResponseEntity.badRequest().body(validateMessage);
        
        FictionDto createFictionDto = fictionService.createFiction(fictionDto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createFictionDto);
    }
    
    private String validate(ProfessionalLiteratureDto dto){
        
        if(dto.getName() == null || dto.getScientificArea() == null)
            return "All fields are required!";
        
        if(dto.getName().isEmpty() || !(dto.getName() instanceof String))
            return "Name should not be empty!";
            
        if(dto.getYear() < 0 || dto.getYear() > LocalDate.now().getYear())
            return "Year must be a valid number!";
            
        if(dto.getScientificArea().isEmpty() || !(dto.getScientificArea() instanceof String))
            return "Scientific area should not be empty!";
        
        return null;
    }
    
    private String validate(FictionDto dto){
        
        if(dto.getName() == null || dto.getGenre()== null || dto.getTheme() == null || dto.getWonPrizes() == null)
            return "All fields are required!";
        
        if(dto.getName().isEmpty() || !(dto.getName() instanceof String))
            return "Name should not be empty!";
            
        if(dto.getYear() < 0 || dto.getYear() > LocalDate.now().getYear())
            return "Year must be a valid number!";
            
        if(dto.getGenre().isEmpty() || !(dto.getGenre()instanceof String))
            return "Genre should not be empty!";
        
        if(dto.getTheme().isEmpty() || !(dto.getTheme()instanceof String))
            return "Theme should not be empty!";
        
        if(dto.getWonPrizes().isEmpty() || !(dto.getWonPrizes()instanceof String))
            return "Won prizes should not be empty!";
        
        return null;
    }
    
    @PatchMapping("/pliterature/{id}")
    public ResponseEntity<?> saveBook(@RequestBody ProfessionalLiteratureDto dto, @PathVariable Long id){

        
        ProfessionalLiteratureDto plDto = plService.saveProfessionalLiterature(id, dto);
        

        if (plDto == null) {
            return ResponseEntity.badRequest().body("There is no book with the given ID!");
        }
        
        return ResponseEntity.ok(plDto);
    }
    
    
    @PatchMapping("/fiction/{id}")
    public ResponseEntity<?> saveBook(@RequestBody FictionDto dto, @PathVariable Long id){
        
        FictionDto fictionDto = fictionService.saveFiction(id, dto);
        

        if (fictionDto == null) {
            return ResponseEntity.badRequest().body("There is no book with the given ID!");
        }
        
        return ResponseEntity.ok(fictionDto);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<?> getBook(@PathVariable Long id){
        
        List<Author> authors = authorService.getAuthorsByBookId(id);
        Object obj = bookService.getBook(id, authors);
        
        if(obj == null)
            return ResponseEntity.badRequest().body("There is no book with the given ID!");
        
        return ResponseEntity.ok(obj);
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){
        
        if(bookService.deleteBook(id) == false)
            return ResponseEntity.badRequest().body("There is no book with the given ID!");
        
        return ResponseEntity.ok("Book is succesfully deleted!");
        
    }
    
    @GetMapping("/search")
    public ResponseEntity<?> getBook(@RequestBody BookDto bookDto){
        
        System.out.println("bookdto: " + bookDto.toString());
        List<Object> list = bookService.getBooks(bookDto);
        
        if(list.size() == 0)
            return ResponseEntity.badRequest().body("There are no books matching the given criteria!");
        
        return ResponseEntity.ok(list);
        
    }
}
