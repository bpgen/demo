package com.anz.demo.repository;

import com.anz.demo.dto.CreditType;
import com.anz.demo.dto.Currency;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@Entity(name = "transactions")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transaction_number;
    private LocalDate value_date;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Enumerated(EnumType.STRING)
    private CreditType debit_credit;
    private BigDecimal amount;
    private String narrative;
    @ManyToOne
    @JoinColumn(name = "account_number")
    private AccountEntity accountEntity;
}
