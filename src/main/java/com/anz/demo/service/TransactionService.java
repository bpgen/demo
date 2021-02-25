package com.anz.demo.service;

import com.anz.demo.dto.CreditType;
import com.anz.demo.dto.Transaction;
import com.anz.demo.repository.AccountEntity;
import com.anz.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Transaction> getAllTransactions(Long accountNumber) {
        AccountEntity accountEntity = accountRepository.findByNumber(accountNumber);
        return convertToTransactionDto(accountEntity);
    }

    private List<Transaction> convertToTransactionDto(AccountEntity accountEntity) {
        List<Transaction> transactions = accountEntity.getTransactionEntities().stream()
                .map(e -> {
                    Transaction transaction = Transaction.builder()
                            .accountNumber(accountEntity.getNumber())
                            .accountName(accountEntity.getName())
                            .valueDate(e.getValue_date())
                            .currency(e.getCurrency())
                            .debitAmount(e.getDebit_credit() == CreditType.DEBIT ? e.getAmount() : null)
                            .creditAmount(e.getDebit_credit() == CreditType.CREDIT ? e.getAmount() : null)
                            .debitCredit(e.getDebit_credit())
                            .transactionNarrative(e.getNarrative())
                            .build();
                    return transaction;
                }).collect(Collectors.toList());
        return transactions;
    }
}
