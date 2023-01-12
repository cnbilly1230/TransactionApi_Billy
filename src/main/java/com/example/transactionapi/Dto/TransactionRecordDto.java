package com.example.transactionapi.Dto;

import com.example.transactionapi.CurrencyEnum.Currency;
import com.example.transactionapi.Model.TransactionRecord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRecordDto {

    private String uid;
    private String tid;
    private Currency currency;
    private BigDecimal amount;
    private String accountIBAN;
    private LocalDate valueDate;
    private String description;

    public static TransactionRecordDto toDto (TransactionRecord record){
        return TransactionRecordDto.builder()
                .uid(record.getUid())
                .tid(record.getTid())
                .currency(record.getCurrency())
                .amount(record.getAmount())
                .accountIBAN(record.getAccountIBAN())
                .valueDate(record.getValueDate())
                .description(record.getDescription())
                .build();
    }
}
