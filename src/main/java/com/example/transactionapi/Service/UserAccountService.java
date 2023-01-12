package com.example.transactionapi.Service;

import com.example.transactionapi.Repository.UserAccountRepository;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;


    public String findUIDByIBAN (String IBAN) throws ResourceNotFoundException {
        Optional<String> opt = userAccountRepository.findUIDByIBAN(IBAN);
        if(opt.isEmpty()){
            throw new ResourceNotFoundException("uid not found by account ["+ IBAN +"]");
        }
        return opt.get();
    }
}
