package com.example.transactionapi.Repository;

import com.example.transactionapi.Model.TransactionRecord;
import com.example.transactionapi.Model.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    @Query("SELECT u.uid FROM UserAccount u WHERE u.accountIBAN = :accountIBAN")
    Optional<String> findUIDByIBAN(@Param("accountIBAN") String IBAN);
}
