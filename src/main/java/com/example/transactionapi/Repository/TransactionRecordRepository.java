package com.example.transactionapi.Repository;

import com.example.transactionapi.Model.TransactionRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRecordRepository extends JpaRepository<TransactionRecord, Long> {
    @Query("SELECT t FROM TransactionRecord t WHERE t.uid = :uid " +
    "AND t.calenderMonth = :month " +
    "AND t.calenderYear = :year")
    Optional<Page<TransactionRecord>> findRecordByUID(@Param("uid") String uid,
                                                      @Param("month") Integer month,
                                                      @Param("year") Integer year,
                                                      Pageable pageable);
}
