package com.example.transactionapi.Dto.Factory;

import com.example.transactionapi.Dto.TransactionMessageDto;
import com.example.transactionapi.Model.TransactionRecord;
import com.example.transactionapi.Service.UserAccountService;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionMessageDtoFactory {

    @Autowired
    UserAccountService userAccountService;


    public TransactionRecord toRecordEntity(TransactionMessageDto message) throws ResourceNotFoundException {
    return TransactionRecord.builder()
            .uid(userAccountService.findUIDByIBAN(message.getAccountIBAN()))
            .tid(message.getTid())
            .currency(message.getCurrency())
            .amount(message.getAmount())
            .accountIBAN(message.getAccountIBAN())
            .valueDate(message.getValueDate())
            .description(message.getDescription())
            .calenderMonth(message.getValueDate().getMonthValue())
            .calenderYear(message.getValueDate().getYear())
            .build();
    }
}
