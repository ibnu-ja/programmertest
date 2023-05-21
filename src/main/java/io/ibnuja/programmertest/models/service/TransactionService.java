package io.ibnuja.programmertest.models.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import io.ibnuja.programmertest.models.entity.Transaction;
import io.ibnuja.programmertest.models.repository.TransactionRepository;
import lombok.extern.java.Log;

@Log
@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Iterable<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    public Transaction create(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Iterable<Transaction> getByCustomerAccount(int customerAccountId) {
        return transactionRepository.findByAccountId(customerAccountId);
    }

    public int calculatePoints(Transaction transaction) {
        int points = 0;
        String description = transaction.getDescription();
        BigDecimal amount = transaction.getAmount();

        if (description.equalsIgnoreCase("beli pulsa")) {
            if (amount.compareTo(BigDecimal.ZERO) > 0 && amount.compareTo(new BigDecimal("10000")) <= 0) {
                points = 0;
            } else if (amount.compareTo(new BigDecimal("10001")) > 0 && amount.compareTo(new BigDecimal("30000")) <= 0) {
                points = amount.divide(new BigDecimal("1000")).intValue();
            } else if (amount.compareTo(new BigDecimal("30001")) > 0) {
                points = amount.divide(new BigDecimal("1000")).multiply(new BigDecimal("2")).intValue();
            }
            
            log.info("beli pulsa dengan amount: " + amount+ " points: " + points);
        } else if (description.equalsIgnoreCase("bayar listrik")) {
            if (amount.compareTo(BigDecimal.ZERO) > 0 && amount.compareTo(new BigDecimal("50000")) <= 0) {
                points = 0;
            } else if (amount.compareTo(new BigDecimal("50001")) > 0 && amount.compareTo(new BigDecimal("100000")) <= 0) {
                points = amount.divide(new BigDecimal("2000")).intValue();
            } else if (amount.compareTo(new BigDecimal("100001")) > 0) {
                points = amount.divide(new BigDecimal("2000")).multiply(new BigDecimal("2")).intValue();
            }
            log.info("bayar listrik dengan amount: " + amount+ " points: " + points);
        }

        return points;
    }
}
