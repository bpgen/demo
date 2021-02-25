package com.anz.demo.repository;

import com.anz.demo.dto.AccountType;
import com.anz.demo.dto.Currency;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity(name = "accounts")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;
    private String name;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @OneToMany(mappedBy = "accountEntity")
    private Set<TransactionEntity> transactionEntities;
}
