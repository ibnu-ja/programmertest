package io.ibnuja.programmertest.models.service;

import org.springframework.stereotype.Service;

import io.ibnuja.programmertest.models.entity.CustomerAccount;
import io.ibnuja.programmertest.models.repository.CustomerAccountRepository;

@Service
public class CustomerAccountService {
    private final CustomerAccountRepository customerAccountRepository;

    public CustomerAccountService(CustomerAccountRepository customerAccountRepository) {
        this.customerAccountRepository = customerAccountRepository;
    }

    public Iterable<CustomerAccount> getAll() {
        return customerAccountRepository.findAll();
    }

    public CustomerAccount create(CustomerAccount customerAccount) {
        return customerAccountRepository.save(customerAccount);
    }
}
