package com.example.transactionapi.Dto.Factory;

import com.example.transactionapi.CurrencyEnum.Currency;
import com.example.transactionapi.Dto.TransactionRecordDto;
import com.example.transactionapi.Dto.TransactionRecordWrapperDto;
import com.example.transactionapi.Service.ExtractRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionRecordWrapperDtoFactory {

    @Autowired
    ExtractRateService extractRateService;

    public TransactionRecordWrapperDto toDto (List<TransactionRecordDto> transactions, Integer pageCounts, Currency baseCurrency) {

        List<BigDecimal> amountInBaseCurrency = convertToBaseCurrency(transactions,baseCurrency);

        List<BigDecimal> debits = new ArrayList<>();
        List<BigDecimal> credits = new ArrayList<>();

        for(BigDecimal amount : amountInBaseCurrency){
            if(amount.compareTo(BigDecimal.ZERO) < 0){
                credits.add(amount);
            }
            else{
                debits.add(amount);
            }
        }

        return TransactionRecordWrapperDto.builder()
                .transactionRecordDtos(transactions)
                .totalDebit(calculateTotalDebit(debits))
                .totalCredit(calculateTotalCredit(credits))
                .pageCount(pageCounts)
                .build();
    }

    public List<BigDecimal> convertToBaseCurrency(List<TransactionRecordDto> transactions, Currency baseCurrency){
        List<BigDecimal> amountInBaseCurrency = new ArrayList<>();
        for(TransactionRecordDto transaction : transactions){
            BigDecimal exchangeRate = extractRateService.getRateByCurrency(baseCurrency)
                    .divide(extractRateService.getRateByCurrency(
                            transaction.getCurrency()), 10,RoundingMode.HALF_UP);
            BigDecimal principal = transaction.getAmount()
                    .multiply(exchangeRate).setScale(4,RoundingMode.HALF_UP);
            amountInBaseCurrency.add(principal);
        }
        return amountInBaseCurrency;
    }

    public BigDecimal calculateTotalDebit (List<BigDecimal> debits){
        BigDecimal totalDebits = BigDecimal.ZERO;
        for(BigDecimal debit : debits){

            totalDebits = totalDebits.add(debit);
        }
        System.out.println(totalDebits);
        return totalDebits;
    }

    public BigDecimal calculateTotalCredit(List<BigDecimal> credits){
        BigDecimal totalCredits = BigDecimal.ZERO;
        for(BigDecimal credit : credits){

            totalCredits = totalCredits.add(credit);
        }
        return totalCredits.negate();
    }
}
