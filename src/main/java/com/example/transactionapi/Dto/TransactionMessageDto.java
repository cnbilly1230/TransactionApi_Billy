package com.example.transactionapi.Dto;

import com.example.transactionapi.CurrencyEnum.Currency;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionMessageDto {
    private String tid;
    private Currency currency;
    private BigDecimal amount;
    private String accountIBAN;
    private LocalDate valueDate;
    private String description;

}
