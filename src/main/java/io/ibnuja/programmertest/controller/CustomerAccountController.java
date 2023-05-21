package io.ibnuja.programmertest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.ibnuja.programmertest.models.entity.CustomerAccount;
import io.ibnuja.programmertest.models.entity.Transaction;
import io.ibnuja.programmertest.models.service.CustomerAccountService;
import io.ibnuja.programmertest.models.service.TransactionService;
import io.ibnuja.programmertest.request.CreateCustomerAccountRequest;
import io.ibnuja.programmertest.request.CreateTransactionRequest;

@RestController
@RequestMapping("/api/account")
public class CustomerAccountController {
    @Autowired
    private CustomerAccountService customerAccountService;

    @Autowired
    private TransactionService transactionService;

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

    @PostMapping("/{accountId}/transaction")
    public Transaction storeTransaction(@PathVariable int accountId, @RequestBody CreateTransactionRequest request) {
        Transaction transaction = new Transaction();
        transaction.setAccountId(accountId);
        transaction.setTransactionDate(request.getTransactionDate());
        transaction.setDescription(request.getDescription());
        transaction.setDebitCreditStatus(request.getDebitCreditStatus());
        transaction.setAmount(request.getAmount());

        return transactionService.create(transaction);
    }
}
