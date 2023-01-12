package com.example.transactionapi;

import com.example.transactionapi.CurrencyEnum.Currency;
import com.example.transactionapi.Model.TransactionRecord;
import com.example.transactionapi.Model.UserAccount;
import com.example.transactionapi.Repository.TransactionRecordRepository;
import com.example.transactionapi.Repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.UUID.randomUUID;

@Component
public class DataLoader {

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    TransactionRecordRepository transactionRecordRepository;

    @PostConstruct
    public void init () throws IOException {
        String uid1 = "r8X8AR3gLSbgDSGFUAwUiEJEh5k2";
        String uid2 = "a3TaFzwsgqaUenGZDyFdHwEI74y2";
        String uid3 = "09bnsKdIo3bfpMcLgZOwAeBZe842";

        ArrayList<String> userAccounts = new ArrayList<>();
        for(int i = 1; i <4; i++){
            for(Currency ccy : Currency.values()){
                StringBuilder sb = new StringBuilder();
                sb.append("user");
                sb.append(i);
                sb.append("-");
                sb.append(ccy.toString());
                sb.append("-account-IBAN");
                String accountIBAN = sb.toString();
                userAccounts.add(accountIBAN);
            }
        }
        ArrayList<UserAccount> users = new ArrayList<>();
        for(int i = 0; i <32; i ++){
            UserAccount user = UserAccount.builder()
                .uid(uid1)
                .accountIBAN(userAccounts.get(i))
                .build();
            users.add(user);
        }for(int i = 32; i <64; i ++){
            UserAccount user = UserAccount.builder()
                .uid(uid2)
                .accountIBAN(userAccounts.get(i))
                .build();
            users.add(user);
        }for(int i = 64; i <96; i ++){
            UserAccount user = UserAccount.builder()
                .uid(uid3)
                .accountIBAN(userAccounts.get(i))
                .build();
            users.add(user);
        }

        userAccountRepository.saveAll(users);

        ArrayList<TransactionRecord> records = new ArrayList<>();
        for(int i =0; i < 10; i++) {
            LocalDate valueDate = LocalDate.now();
            TransactionRecord transactionRecord = TransactionRecord.builder()
                    .uid(uid1)
                    .tid(randomUUID().toString())
                    .currency(Currency.USD)
                    .accountIBAN("user1-USD-account-IBAN")
                    .amount(BigDecimal.valueOf(ThreadLocalRandom.current().nextInt(-100000,100000)))
                    .valueDate(valueDate)
                    .description("Online Payment USD")
                    .calenderYear(valueDate.getYear())
                    .calenderMonth(valueDate.getMonthValue())
                    .build();
            records.add(transactionRecord);
        }

        transactionRecordRepository.saveAll(records);

    }


}
