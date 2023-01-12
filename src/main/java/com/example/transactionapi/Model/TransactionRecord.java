package com.example.transactionapi.Model;

import com.example.transactionapi.CurrencyEnum.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction_records")
public class TransactionRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "user_id", nullable = false)
    private String uid;

    @NotBlank
    @Column(nullable = false)
    private String tid;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @NotNull
    @Column(nullable = false)
    private BigDecimal amount;

    @NotBlank
    @Column(nullable = false)
    private String accountIBAN;

    @NotNull
    @Column(nullable = false)
    private LocalDate valueDate;

    @NotBlank
    @Column(nullable = false)
    private String description;

    @NotNull
    @Column(nullable = false)
    private Integer calenderMonth;

    @NotNull
    @Column(nullable = false)
    private Integer calenderYear;
}
