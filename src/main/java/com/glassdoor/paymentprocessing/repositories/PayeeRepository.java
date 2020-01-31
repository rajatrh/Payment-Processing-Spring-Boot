package com.glassdoor.paymentprocessing.repositories;

import com.glassdoor.paymentprocessing.domain.Payee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PayeeRepository extends JpaRepository<Payee, Long> {

    Payee findByUserName(String userName);

}
