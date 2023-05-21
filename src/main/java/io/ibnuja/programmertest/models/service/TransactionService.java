package io.ibnuja.programmertest.models.service;

import org.springframework.stereotype.Service;

import io.ibnuja.programmertest.models.entity.Transaction;
import io.ibnuja.programmertest.models.repository.TransactionRepository;

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
}
