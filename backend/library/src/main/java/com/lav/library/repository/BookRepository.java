/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lav.library.repository;

import com.lav.library.domain.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lav Jovanovic
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    public List<Book> findByName(String name);

    public List<Book> findByYear(int year);

    public List<Book> findByYearAndName(int year, String name);

}
