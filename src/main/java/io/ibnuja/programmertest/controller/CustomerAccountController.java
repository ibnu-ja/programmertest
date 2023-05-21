package io.ibnuja.programmertest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.ibnuja.programmertest.models.entity.CustomerAccount;
import io.ibnuja.programmertest.models.service.CustomerAccountService;
import io.ibnuja.programmertest.request.CreateCustomerAccountRequest;

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

    @PostMapping
    public CustomerAccount store(@RequestBody CreateCustomerAccountRequest request) {
        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setName(request.getName());

        return customerAccountService.create(customerAccount);
    }
}
