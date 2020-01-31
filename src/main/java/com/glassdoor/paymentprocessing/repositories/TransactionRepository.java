package com.glassdoor.paymentprocessing.repositories;

import com.glassdoor.paymentprocessing.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
