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
import jakarta.validation.Valid;
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
 * Rest kontroler koji upravlja HTTP zahtevima vezanim za knjige Pruza krajnje
 * tačke za kreiranje, dobijanje, azuriranje i brisanje profesionalne literature
 * i beletrisike
 *
 * @author Lav Jovanovic
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

    /**
     * Vraca sve knjige
     *
     * @return ResponseEntity<?> Lista svih knjiga ili poruka o gresci
     */
    @GetMapping
    public ResponseEntity<?> getAllBooks() {

        List<BookDto> allBooks = bookService.getAllBooks();

        if (allBooks.isEmpty()) {
            return ResponseEntity.badRequest().body("There are no books");
        }

        return ResponseEntity.ok(allBooks);

    }

    /**
     * Kreira novu knjigu iz oblasti strucne literature
     *
     * @param literatureDto DTO objekat strucne literature
     * @return kreirani objekat ili poruka o gresci
     */
    @PostMapping("/pliterature")
    public ResponseEntity<?> createBook(@Valid @RequestBody ProfessionalLiteratureDto literatureDto) {

        ProfessionalLiteratureDto createPlDto = plService.createProfessionalLiterature(literatureDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createPlDto);
    }

    /**
     * Kreira novu knjigu iz oblasti beletristike
     *
     * @param fictionDto DTO objekat beletristike
     * @return kreirani objekat ili poruka o gresci
     */
    @PostMapping("/fiction")
    public ResponseEntity<?> createBook(@Valid @RequestBody FictionDto fictionDto) {

        FictionDto createFictionDto = fictionService.createFiction(fictionDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createFictionDto);
    }


    /**
     * Azurira knjigu iz oblasti strucne literature sa datim id-om
     *
     * @param dto DTO strucne literature
     * @param id id knjige
     * @return azurirani objekat ili poruka o gresci
     */
    @PatchMapping("/pliterature/{id}")
    public ResponseEntity<?> saveBook(@RequestBody ProfessionalLiteratureDto dto, @PathVariable Long id) {

        ProfessionalLiteratureDto plDto = plService.saveProfessionalLiterature(id, dto);

        if (plDto == null) {
            return ResponseEntity.badRequest().body("There is no book with the given ID!");
        }

        return ResponseEntity.ok(plDto);
    }

    /**
     * Azurira knjigu iz oblasti fikcije sa datim id-om
     *
     * @param dto DTO beletristike
     * @param id id knjige
     * @return azurirani objekat ili poruka o gresci
     */
    @PatchMapping("/fiction/{id}")
    public ResponseEntity<?> saveBook(@RequestBody FictionDto dto, @PathVariable Long id) {

        FictionDto fictionDto = fictionService.saveFiction(id, dto);

        if (fictionDto == null) {
            return ResponseEntity.badRequest().body("There is no book with the given ID!");
        }

        return ResponseEntity.ok(fictionDto);
    }

    /**
     * Vraca knjigu sa datim id-om.
     *
     * @param id id knjige
     * @return objekat knjige ili poruka o gresci
     */
    @GetMapping("{id}")
    public ResponseEntity<?> getBook(@PathVariable Long id) {

        List<Author> authors = authorService.getAuthorsByBookId(id);
        Object obj = bookService.getBook(id, authors);

        if (obj == null) {
            return ResponseEntity.badRequest().body("There is no book with the given ID!");
        }

        return ResponseEntity.ok(obj);
    }

    /**
     * Brise knjigu sa datim id-om
     *
     * @param id id knjige.
     * @return poruka o uspesnom brisanju ili gresci
     */
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {

        if (bookService.deleteBook(id) == false) {
            return ResponseEntity.badRequest().body("There is no book with the given ID!");
        }

        return ResponseEntity.ok("Book is succesfully deleted!");

    }

    /**
     * Pretrazuje knjige po zadatim kriterijumima
     *
     * @param bookDto DTO objekat za pretragu
     * @return lista knjiga koje zadovoljavaju kriterijume ili poruka o gresci
     */
    @GetMapping("/search")
    public ResponseEntity<?> getBook(@RequestBody BookDto bookDto) {

        List<Object> list = bookService.getBooks(bookDto);

        if (list.size() == 0) {
            return ResponseEntity.badRequest().body("There are no books matching the given criteria!");
        }

        return ResponseEntity.ok(list);

    }
}
