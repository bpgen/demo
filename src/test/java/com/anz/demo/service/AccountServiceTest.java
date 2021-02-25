package com.anz.demo.service;

import com.anz.demo.dto.Account;
import com.anz.demo.dto.AccountType;
import com.anz.demo.dto.CreditType;
import com.anz.demo.dto.Currency;
import com.anz.demo.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private CurrencyConverterRepository currencyConverterRepository;

    @Test
    public void test_getAllAccounts() {
        when(accountRepository.findAll()).thenReturn(getAccountEntity());
        CurrencyConverterEntity currencyConverterEntity = new CurrencyConverterEntity();
        currencyConverterEntity.setSource(Currency.SGD);
        currencyConverterEntity.setTarget(Currency.USD);
        currencyConverterEntity.setRate(new BigDecimal("1.75"));
        when(currencyConverterRepository.findBySourceAndTarget(Currency.SGD,Currency.USD)).thenReturn(currencyConverterEntity);
        List<Account> accountList = accountService.getAllAccounts();
        assertTrue(accountList.size()==2);
        assertTrue(accountList.get(0).getOpeningBalance().doubleValue()==1.25);
    }

    private List<AccountEntity> getAccountEntity() {
        List<AccountEntity> accountEntityList = new ArrayList<>();
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setNumber(887878701L);
        accountEntity.setName("Account-1");
        accountEntity.setType(AccountType.SAVING_AC);
        accountEntity.setCurrency(Currency.SGD);
        accountEntity.setTransactionEntities(getTransactionEntities_887878701());
        accountEntityList.add(accountEntity);

        AccountEntity accountEntity2 = new AccountEntity();
        accountEntity2.setNumber(887878702l);
        accountEntity2.setName("Account-2");
        accountEntity2.setType(AccountType.CURRENT_AC);
        accountEntity2.setCurrency(Currency.USD);
        accountEntityList.add(accountEntity2);

        return accountEntityList;
    }

    private Set<TransactionEntity> getTransactionEntities_887878701() {
        Set<TransactionEntity> transactionEntities = new HashSet<>();
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setNumber(887878701l);

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
        return transactionEntities;
    }

}