package com.anz.demo.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
public class Transaction {
    private Long accountNumber;
    private String accountName;
    private LocalDate valueDate;
    private Currency currency;
    private BigDecimal debitAmount;
    private BigDecimal creditAmount;
    private CreditType debitCredit;
    private String transactionNarrative;

}
