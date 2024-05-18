/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.lav.library.mapper;

import com.lav.library.domain.Author;
import com.lav.library.dto.AuthorDto;
import com.lav.library.mapper.AuthroMapper;
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
public class AuthroMapperTest {
    
    @Test
    public void testMapToAuthorDto() {
        Author author = new Author();
        author.setAuthorId(1L);
        author.setFirstName("Marko");
        author.setLastName("Markovic");

        AuthorDto authorDto = AuthroMapper.mapToAuthorDto(author);

        assertNotNull(authorDto);
        assertEquals(author.getAuthorId(), authorDto.getAuthorId());
        assertEquals(author.getFirstName(), authorDto.getFirstName());
        assertEquals(author.getLastName(), authorDto.getLastName());
    }

    @Test
    public void testMapToAuthor() {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setAuthorId(1L);
        authorDto.setFirstName("Marko");
        authorDto.setLastName("Markovic");

        Author author = AuthroMapper.mapToAuthor(authorDto);

        assertNotNull(author);
        assertEquals(authorDto.getAuthorId(), author.getAuthorId());
        assertEquals(authorDto.getFirstName(), author.getFirstName());
        assertEquals(authorDto.getLastName(), author.getLastName());
    }

    
}
