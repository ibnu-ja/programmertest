package io.ibnuja.programmertest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.ibnuja.programmertest.models.entity.CustomerAccount;
import io.ibnuja.programmertest.models.service.CustomerAccountService;

@RestController
@RequestMapping("/api/account")
public class CustomerAccountController {
    private final CustomerAccountService customerAccountService;

    public CustomerAccountController(CustomerAccountService customerAccountService) {
        this.customerAccountService = customerAccountService;
    }

    @GetMapping
    public Iterable<CustomerAccount> index() {
        return customerAccountService.getAll();
    }
    
}
