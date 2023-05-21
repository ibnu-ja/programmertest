package io.ibnuja.programmertest.models.repository;

import org.springframework.data.repository.CrudRepository;

import io.ibnuja.programmertest.models.entity.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
    
}
