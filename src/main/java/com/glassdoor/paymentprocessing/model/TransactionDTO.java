package com.glassdoor.paymentprocessing.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDTO implements Serializable {

    private LocalDate transactionDate = LocalDate.now();
    private Float transactionAmount;
    private String receiptNumber;
    private Long payeeId;

}
