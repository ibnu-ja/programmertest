package io.ibnuja.programmertest.models.repository;

import java.time.LocalDate;

import org.springframework.data.repository.CrudRepository;

import io.ibnuja.programmertest.models.entity.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
    Iterable<Transaction> findByAccountId(int accountId);
    
    Iterable<Transaction> findByAccountIdAndTransactionDateBetween(int accountId, LocalDate startDate, LocalDate endDate);
}
