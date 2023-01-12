package com.example.transactionapi.Dto;

import com.example.transactionapi.Model.TransactionRecord;
import com.example.transactionapi.config.TransactionSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRecordWrapperDto {
    private List<TransactionRecordDto> transactionRecordDtos;

    @JsonProperty
    @JsonSerialize(using = TransactionSerializer.class)
    private BigDecimal totalDebit;

    @JsonProperty
    @JsonSerialize(using = TransactionSerializer.class)
    private BigDecimal totalCredit;

    private Integer pageCount;
}
