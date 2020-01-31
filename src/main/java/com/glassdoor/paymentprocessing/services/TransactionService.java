package com.glassdoor.paymentprocessing.services;

import com.glassdoor.paymentprocessing.domain.Payee;
import com.glassdoor.paymentprocessing.domain.Transaction;
import com.glassdoor.paymentprocessing.model.TransactionVerificationDTO;

public interface TransactionService {
    Payee findPayee(String userName);

    boolean verifyTransaction(TransactionVerificationDTO transactionVerificationDTO, Payee user);

    Transaction insertTransaction(TransactionVerificationDTO transactionVerificationDTO, Payee user);

}
