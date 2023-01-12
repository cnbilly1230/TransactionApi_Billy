package com.example.transactionapi.Kafka;

import com.example.transactionapi.CurrencyEnum.Currency;
import com.example.transactionapi.Dto.TransactionMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.UUID.randomUUID;

@Service
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, TransactionMessageDto> kafkaTemplate;

    private String kafkaTopic = "January";


    @Scheduled(fixedRate = 5000)
    public void produce() {
        String tid = randomUUID().toString();

        int ran = ThreadLocalRandom.current().nextInt(Currency.values().length);
        Currency currency = Currency.values()[ran];

        BigDecimal amount = BigDecimal.valueOf(ThreadLocalRandom.current().nextInt(-100000,100000));

        StringBuilder sb = new StringBuilder();
        sb.append("user");
        int num = ThreadLocalRandom.current().nextInt(1,4);
        sb.append(num);
        sb.append("-");
        sb.append(currency.toString());
        sb.append("-account-IBAN");
        String accountIBAN = sb.toString();

        LocalDate start = LocalDate.of(2013,1,6);
        LocalDate end = LocalDate.now();
        LocalDate valueDate = LocalDate.ofEpochDay(ThreadLocalRandom.current().nextLong(start.toEpochDay(),end.toEpochDay()));

        StringBuilder sb2 = new StringBuilder();
        sb2.append("Online Payment ");
        sb2.append(currency);
        String description = sb2.toString();

        TransactionMessageDto transactionMessage = TransactionMessageDto.builder()
                            .tid(tid)
                            .currency(currency)
                            .amount(amount)
                            .accountIBAN(accountIBAN)
                            .valueDate(valueDate)
                            .description(description)
                            .build();

        System.out.println("publish message to topic");

        kafkaTemplate.send(kafkaTopic,transactionMessage);
    }
}
