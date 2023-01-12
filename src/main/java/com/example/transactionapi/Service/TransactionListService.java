package com.example.transactionapi.Service;

import com.example.Exception.ResourceNotFoundException;
import com.example.transactionapi.Dto.TransactionRecordDto;
import com.example.transactionapi.Model.TransactionRecord;
import com.example.transactionapi.Repository.TransactionRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionListService {
    @Autowired
    private TransactionRecordRepository transactionRecordRepository;


    public Page<TransactionRecord> findByUID(Integer month, Integer year, String uid, Integer page) throws ResourceNotFoundException {
        Pageable pageable = PageRequest.of( page - 1, 5);
        Optional<Page<TransactionRecord>> opt = transactionRecordRepository.findRecordByUID(uid, month, year, pageable);
        if(opt.isEmpty()){
            throw new ResourceNotFoundException("Records not found by uid ["+ uid +"]" +
                    " year [" + year + "]" +
                    " month [" + month + "]" +
                    " page [" + page + "]" );
        }
        return opt.get();

    }


}
