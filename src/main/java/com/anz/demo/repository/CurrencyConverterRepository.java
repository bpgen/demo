package com.anz.demo.repository;

import com.anz.demo.dto.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyConverterRepository extends JpaRepository<CurrencyConverterEntity, Long> {
    CurrencyConverterEntity findBySourceAndTarget(Currency source, Currency target);
}
