package com.glassdoor.paymentprocessing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Transaction extends BaseEntity {

    @NotNull
    @Column(nullable = false)
    @CreatedDate
    private LocalDate transactionDate = LocalDate.now();

    @NotNull
    @Column(nullable = false)
    private Float transactionAmount;

    private String receiptNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Payee payee;

}
