package com.glassdoor.paymentprocessing.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionVerificationDTO implements Serializable {

    private String userName;
    private Float transactionAmount;
    private String expiryDate;
    private Integer cvv;
    private String billingAddress;
    private String cardNumber;

}
