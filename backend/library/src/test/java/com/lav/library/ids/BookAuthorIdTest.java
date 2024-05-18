/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.ids;

import com.lav.library.domain.Author;
import com.lav.library.domain.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Lav
 */
public class BookAuthorIdTest {

    @Test
    public void testBookAuthorId() {
        BookAuthorId bookAuthorId = new BookAuthorId();

        assertNull(bookAuthorId.getBook());
        assertNull(bookAuthorId.getAuthor());
    }

    @Test
    public void testConstructor() {
        Book book = new Book();
        Author author = new Author();
        BookAuthorId bookAuthorId = new BookAuthorId();

        bookAuthorId.setBook(book);
        bookAuthorId.setAuthor(author);

        assertEquals(book, bookAuthorId.getBook());
        assertEquals(author, bookAuthorId.getAuthor());
    }

    @Test
    public void testEquals() {
        Book book1 = new Book();
        Book book2 = new Book();
        Author author1 = new Author();
        Author author2 = new Author();

        BookAuthorId bookAuthorId1 = new BookAuthorId();
        bookAuthorId1.setBook(book1);
        bookAuthorId1.setAuthor(author1);

        BookAuthorId bookAuthorId2 = new BookAuthorId();
        bookAuthorId2.setBook(book1);
        bookAuthorId2.setAuthor(author1);

        BookAuthorId bookAuthorId3 = new BookAuthorId();
        bookAuthorId3.setBook(book2);
        bookAuthorId3.setAuthor(author2);

        assertEquals(bookAuthorId1, bookAuthorId2);
        assertNotEquals(bookAuthorId1, bookAuthorId3);
    }


    @Test
    public void testSetBook() {
        Book book = new Book();
        BookAuthorId bookAuthorId = new BookAuthorId();

        bookAuthorId.setBook(book);
        assertEquals(book, bookAuthorId.getBook());
    }

    @Test
    public void testSetAuthor() {
        Author author = new Author();
        BookAuthorId bookAuthorId = new BookAuthorId();

        bookAuthorId.setAuthor(author);
        assertEquals(author, bookAuthorId.getAuthor());
    }

}
