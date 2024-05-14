/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lav.library.repository;

import com.lav.library.domain.Book;
import com.lav.library.domain.Fiction;
import com.lav.library.domain.ProfessionalLiterature;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lav
 */
@Repository
public interface ProfessionalLiteratureRepository extends JpaRepository<ProfessionalLiterature, Long> {
    
    
    
}
