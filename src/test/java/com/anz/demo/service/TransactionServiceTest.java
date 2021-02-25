package com.anz.demo.service;

import com.anz.demo.dto.CreditType;
import com.anz.demo.dto.Currency;
import com.anz.demo.dto.Transaction;
import com.anz.demo.repository.AccountEntity;
import com.anz.demo.repository.AccountRepository;
import com.anz.demo.repository.TransactionEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private AccountRepository accountRepository;

    @Test
    public void test_getAllTransactions() {
        when(accountRepository.findByNumber(887878701l)).thenReturn(getAccountEntity(887878701l));
        List<Transaction> transactions = transactionService.getAllTransactions(887878701l);
        assertTrue(transactions.size()==2);
    }

    private AccountEntity getAccountEntity(long accountNumber) {

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setNumber(887878701l);

        Set<TransactionEntity> transactionEntities = new HashSet<>();

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setTransaction_number(1l);
        transactionEntity.setAccountEntity(accountEntity);
        transactionEntity.setCurrency(Currency.SGD);
        transactionEntity.setAmount(new BigDecimal(112.50));
        transactionEntity.setDebit_credit(CreditType.CREDIT);
        transactionEntities.add(transactionEntity);

        TransactionEntity transactionEntity2 = new TransactionEntity();
        transactionEntity2.setTransaction_number(2l);
        transactionEntity2.setAccountEntity(accountEntity);
        transactionEntity2.setCurrency(Currency.SGD);
        transactionEntity2.setAmount(new BigDecimal(111.25));
        transactionEntity2.setDebit_credit(CreditType.DEBIT);
        transactionEntities.add(transactionEntity2);

        accountEntity.setTransactionEntities(transactionEntities);
        return accountEntity;
    }

}