package com.example.transactionapi.Kafka;

import com.example.Exception.ResourceNotFoundException;
import com.example.transactionapi.Dto.Factory.TransactionMessageDtoFactory;
import com.example.transactionapi.Dto.TransactionMessageDto;
import com.example.transactionapi.Model.TransactionRecord;
import com.example.transactionapi.Repository.TransactionRecordRepository;
import com.example.transactionapi.Repository.UserAccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;

@Service
public class KafkaConsumer {

    @Autowired
    private TransactionRecordRepository transactionRecordRepository;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    TransactionMessageDtoFactory transactionMessageDtoFactory;

    @KafkaListener(
            topics = "January",
            groupId = "00003"
    )
    void consume(GenericMessage<String> message) throws JsonProcessingException {
        TransactionMessageDto transactionMessageDto = mapper.readValue(message.getPayload(), TransactionMessageDto.class);
        save(transactionMessageDto);
        System.out.println("Listener received" + message.getPayload());
    }

    public void save(TransactionMessageDto message){
        transactionRecordRepository.save(transactionMessageDtoFactory.toRecordEntity(message));

    }
}

