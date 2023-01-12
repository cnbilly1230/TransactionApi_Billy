package com.example.transactionapi.Controller;

import com.example.transactionapi.Kafka.KafkaProducer;
import com.example.transactionapi.Service.ExtractRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    ExtractRateService extractRateService;


    @GetMapping
    public String sendMessage() throws IOException {
        extractRateService.updateRatesBaseHKD();
        return "Message sent successfully";
    }
}
