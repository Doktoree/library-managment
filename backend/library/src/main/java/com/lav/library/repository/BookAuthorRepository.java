/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lav.library.repository;

import com.lav.library.ids.BookAuthorId;
import com.lav.library.domain.*;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lav
 */
@Repository
public interface BookAuthorRepository extends JpaRepository<BookAuthor, BookAuthorId> {
    
    List<BookAuthor> findById_AuthorAuthorId(Long authorId);
    List<BookAuthor> findById_BookBookId(Long bookId);
    //List<BookAuthor> findByIdBookId(Long bookId);
    //List<BookAuthor> findBybookId(Long bookId);
    //List<BookAuthor> findByBookAuthorIdid(Long authorId, Long bookId);
    
}
