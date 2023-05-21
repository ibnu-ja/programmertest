package io.ibnuja.programmertest.models.entity;


import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavingBook {

    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate transactionDate;

    private String description;

    private String debit;

    private String credit;

    private BigDecimal amount;
    
}
