package com.example.transactionapi.Service;

import com.example.transactionapi.CurrencyEnum.Currency;
import com.example.transactionapi.config.ObjectMapperConfig;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.Unsigned;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.DataOutput;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ExtractRateService {
    @Autowired
    private ObjectMapper mapper;
    private final Map<Currency, BigDecimal> rates = new ConcurrentHashMap<>();

@Scheduled(fixedRate = 50000000)
    public void updateRatesBaseHKD() throws IOException {
        WebClient webClient = WebClient.create("https://api.freecurrencyapi.com/v1/latest?apikey=P1QaLjifNeLWzuVZVZjyrWRTwmVJJn51nSjW8PHy&base_currency=HKD");
        Mono<String> response = webClient.get()
                .retrieve()
                .bodyToMono(String.class);
        String jsonBody = response.block();
        JsonNode rootNode = mapper.readTree(jsonBody);
        JsonNode dataNode = rootNode.at("/data");
        rates.putAll(mapper.readValue(
                mapper.treeAsTokens(dataNode),
                new TypeReference<Map<? extends Currency, ? extends BigDecimal>>() {}));


        System.out.println(rates);
        System.out.println(rates.get(Currency.USD));
    }

    public BigDecimal getRateByCurrency(Currency currency){
        return rates.get(currency);
    }
}
