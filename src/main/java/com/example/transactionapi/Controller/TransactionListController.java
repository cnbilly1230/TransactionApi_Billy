package com.example.transactionapi.Controller;

import com.example.Exception.ResourceNotFoundException;
import com.example.transactionapi.CurrencyEnum.Currency;
import com.example.transactionapi.Dto.ApiResponseDto;
import com.example.transactionapi.Dto.Factory.TransactionRecordWrapperDtoFactory;
import com.example.transactionapi.Dto.TransactionRecordDto;
import com.example.transactionapi.Dto.TransactionRecordWrapperDto;
import com.example.transactionapi.Model.TransactionRecord;
import com.example.transactionapi.Service.TransactionListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/transactionLists")
public class TransactionListController {

    @Autowired
    private TransactionListService transactionListService;

    @Autowired
    TransactionRecordWrapperDtoFactory transactionRecordWrapperDtoFactory;

    @GetMapping
    public ApiResponseDto<TransactionRecordWrapperDto> findTransactionByUID (@RequestParam(name = "m") Integer month,
                                                @RequestParam(name = "y") Integer year,
                                                @RequestParam(name = "p", defaultValue = "1") Integer page,
                                                @RequestParam(name = "c") Currency currency,
                                                Principal principal) throws ResourceNotFoundException, IOException {

        Page<TransactionRecord> returnedPage = transactionListService.findByUID(month,year,principal.getName(),page);
        List<TransactionRecordDto> transactions = new ArrayList<>();
        for(TransactionRecord tr : returnedPage.getContent()){
            transactions.add(TransactionRecordDto.toDto(tr));
        }
        return ApiResponseDto.ok(transactionRecordWrapperDtoFactory.toDto(transactions, returnedPage.getTotalPages(),currency));
    }
}
