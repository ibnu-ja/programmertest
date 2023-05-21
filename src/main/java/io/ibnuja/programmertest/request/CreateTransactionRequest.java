package io.ibnuja.programmertest.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class CreateTransactionRequest {

    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate transactionDate;
    private String description;
    private String debitCreditStatus;
    private BigDecimal amount;

}