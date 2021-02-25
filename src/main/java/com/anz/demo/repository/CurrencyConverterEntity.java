package com.anz.demo.repository;

import com.anz.demo.dto.Currency;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "currency_converter")
public class CurrencyConverterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Currency source;
    @Enumerated(EnumType.STRING)
    private Currency target;
    private BigDecimal rate;
}
