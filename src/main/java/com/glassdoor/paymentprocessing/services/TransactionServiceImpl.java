package com.glassdoor.paymentprocessing.services;

import com.glassdoor.paymentprocessing.domain.Payee;
import com.glassdoor.paymentprocessing.domain.Transaction;
import com.glassdoor.paymentprocessing.model.TransactionVerificationDTO;
import com.glassdoor.paymentprocessing.repositories.PayeeRepository;
import com.glassdoor.paymentprocessing.repositories.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Serivce, that does most of the work
 *
 * finds the payee, verifies the transaction and inserts the new transaction.
 */

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

    private final PayeeRepository payeeRepository;
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(PayeeRepository payeeRepository, TransactionRepository transactionRepository) {
        this.payeeRepository = payeeRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Payee findPayee(String userName) {
        Payee user = payeeRepository.findByUserName(userName);
        log.info("User=" + user);
        return user;
    }

    @Override
    public boolean verifyTransaction(TransactionVerificationDTO transactionVerificationDTO, Payee user) {
        boolean isVerifiedByAddress = transactionVerificationDTO.getBillingAddress().equals(user.getBillingAddress());
        log.info("User" + user);
        return isVerifiedByAddress;
    }

    @Override
    public Transaction insertTransaction(TransactionVerificationDTO transactionVerificationDTO, Payee user) {
        Transaction transaction = new Transaction();
        transaction.setPayee(user);
        transaction.setTransactionAmount(transactionVerificationDTO.getTransactionAmount());
        transaction.setTransactionDate(LocalDate.now());
        transaction.setReceiptNumber(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) + "_" + user.getId());
        log.info(String.valueOf(transaction));
        transactionRepository.save(transaction);
        return null;
    }
}
