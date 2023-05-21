package io.ibnuja.programmertest.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.ibnuja.programmertest.models.entity.CustomerAccount;
import io.ibnuja.programmertest.models.entity.CustomerPoint;
import io.ibnuja.programmertest.models.entity.SavingBook;
import io.ibnuja.programmertest.models.entity.Transaction;
import io.ibnuja.programmertest.models.service.CustomerAccountService;
import io.ibnuja.programmertest.models.service.TransactionService;
import io.ibnuja.programmertest.request.CreateCustomerAccountRequest;
import io.ibnuja.programmertest.request.CreateTransactionRequest;
import lombok.extern.java.Log;

@Log
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

    @GetMapping("/{accountId}/transaction")
    public Iterable<Transaction> getTransactionByAccount(@PathVariable int accountId) {
        return transactionService.getByCustomerAccount(accountId);
    }

    @GetMapping("/transaction/points")
    public Iterable<CustomerPoint> getTransactionPoints() {
        Iterable<CustomerAccount> customerAccounts = customerAccountService.getAll();
        List<CustomerPoint> customerPointsList = new ArrayList<>();

        // Loop through all customer accounts
        for (CustomerAccount customerAccount : customerAccounts) {
            int accountId = customerAccount.getId();
            String accountName = customerAccount.getName();

            log.info("Getting transactions for account: " + customerAccount.getName());
            Iterable<Transaction> transactions = transactionService.getByCustomerAccount(accountId);
            int totalPoints = 0;

            for (Transaction transaction : transactions) {
                log.info("transaction type is: " + transaction.getDebitCreditStatus() + " and amount is: "
                        + transaction.getAmount());
                int points = transactionService.calculatePoints(transaction);
                log.info("current points is: " + points);
                totalPoints += points;
            }

            // customerPointsMap.put(accountId, totalPoints);
            CustomerPoint customerPoint = new CustomerPoint(accountId, accountName, totalPoints);
            customerPointsList.add(customerPoint);
        }

        return customerPointsList;
    }

    @GetMapping("/{accountId}/saving-book")
    public Iterable<SavingBook> getSavingBookByAccountAndDateRange(
            @PathVariable int accountId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        Iterable<Transaction> transactions = transactionService.getTransactionsByAccountAndDateRange(accountId,
                startDate, endDate);

        List<SavingBook> savingBookList = new ArrayList<>();

        for (Transaction transaction : transactions) {
            SavingBook savingBook = new SavingBook();
            savingBook.setTransactionDate(transaction.getTransactionDate());
            savingBook.setDescription(transaction.getDescription());
            savingBook.setAmount(transaction.getAmount());

            if (transaction.getDebitCreditStatus().equals("D")) {
                savingBook.setDebit(transaction.getAmount().toString());
                savingBook.setCredit("-");
            } else {
                savingBook.setDebit("-");
                savingBook.setCredit(transaction.getAmount().toString());
            }

            savingBookList.add(savingBook);
        }

        return savingBookList;
    }
}
