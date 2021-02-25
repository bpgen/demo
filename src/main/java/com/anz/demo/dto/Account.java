package com.anz.demo.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
public class Account {
    private Long number;
    private String name;
    private String type;
    private LocalDate balanceDate;
    private Currency currency;
    private BigDecimal openingBalance;
}
