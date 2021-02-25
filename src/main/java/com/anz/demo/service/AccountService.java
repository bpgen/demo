package com.anz.demo.service;

import com.anz.demo.dto.Account;
import com.anz.demo.dto.CreditType;
import com.anz.demo.dto.Currency;
import com.anz.demo.repository.AccountEntity;
import com.anz.demo.repository.AccountRepository;
import com.anz.demo.repository.CurrencyConverterRepository;
import com.anz.demo.repository.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CurrencyConverterRepository currencyConverterRepository;

    public List<Account> getAllAccounts() {
        List<AccountEntity> accountEntities = accountRepository.findAll();
        return convertToAccountDto(accountEntities);
    }

    private List<Account> convertToAccountDto(List<AccountEntity> accountEntities) {
        List<Account> accounts = accountEntities.stream().map(e -> {
            Account account = Account.builder()
                    .number(e.getNumber())
                    .name(e.getName())
                    .type(e.getType().getName())
                    .balanceDate(LocalDate.now())
                    .currency(Currency.SGD)
                    .openingBalance(getNetAmount(e.getTransactionEntities(), Currency.SGD)).build();
            return account;

        }).collect(Collectors.toList());
        return accounts;
    }

    private BigDecimal getNetAmount(Set<TransactionEntity> transactionEntities, Currency targetCurrency) {
        if(Objects.isNull(transactionEntities)) return BigDecimal.ZERO;

        BigDecimal total = transactionEntities.stream().map(e -> {
            BigDecimal conversion = currencyConverterRepository.findBySourceAndTarget(e.getCurrency(), Currency.USD).getRate();
            BigDecimal convertedAmount = e.getAmount().multiply(conversion, MathContext.DECIMAL32);
            BigDecimal finalAmount = e.getDebit_credit() == CreditType.CREDIT ? convertedAmount : convertedAmount.negate();
            return finalAmount;
        }).reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal conversionBackToTargetCurrency = currencyConverterRepository.findBySourceAndTarget(targetCurrency, Currency.USD).getRate();
        BigDecimal amountInRequiredCurrency =  total.divide(conversionBackToTargetCurrency, MathContext.DECIMAL32);
        return amountInRequiredCurrency;
    }
}
