/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lav.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Lav
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoanItemDto {
    
    private Long loanItemId;
    private Long loanId;
    private String status = "not returned";
    private Long bookId;
    
}
