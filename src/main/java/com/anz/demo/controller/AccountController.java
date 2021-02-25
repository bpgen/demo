package com.anz.demo.controller;

import com.anz.demo.dto.Account;
import com.anz.demo.dto.Transaction;
import com.anz.demo.service.AccountService;
import com.anz.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return new ResponseEntity<List<Account>>(accounts, HttpStatus.OK);
    }

    @GetMapping("/transactions/{account_number}")
    public ResponseEntity<List<Transaction>> getAllTransactionsForAccount(@PathVariable("account_number") Long accountNumber) {
        List<Transaction> transactions = transactionService.getAllTransactions(accountNumber);
        return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
    }
}
