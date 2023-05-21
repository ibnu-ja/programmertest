package io.ibnuja.programmertest.models.repository;

import org.springframework.data.repository.CrudRepository;

import io.ibnuja.programmertest.models.entity.CustomerAccount;

public interface CustomerAccountRepository extends CrudRepository<CustomerAccount, Integer> {
    
}
