package com.glassdoor.paymentprocessing.api.v1;

import com.glassdoor.paymentprocessing.domain.Payee;
import com.glassdoor.paymentprocessing.domain.Transaction;
import com.glassdoor.paymentprocessing.model.TransactionDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-01-31T02:45:03-0500",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_241 (Oracle Corporation)"
)
@Component
public class TransactionMapperImpl implements TransactionMapper {

    @Override
    public Transaction transactionDtoToTransaction(TransactionDTO transactionDTO) {
        if ( transactionDTO == null ) {
            return null;
        }

        Transaction transaction = new Transaction();

        transaction.setTransactionDate( transactionDTO.getTransactionDate() );
        transaction.setTransactionAmount( transactionDTO.getTransactionAmount() );
        transaction.setReceiptNumber( transactionDTO.getReceiptNumber() );

        return transaction;
    }

    @Override
    public TransactionDTO transactionToTransactionDto(Transaction transaction) {
        if ( transaction == null ) {
            return null;
        }

        TransactionDTO transactionDTO = new TransactionDTO();

        transactionDTO.setPayeeId( transactionPayeeId( transaction ) );
        transactionDTO.setTransactionDate( transaction.getTransactionDate() );
        transactionDTO.setTransactionAmount( transaction.getTransactionAmount() );
        transactionDTO.setReceiptNumber( transaction.getReceiptNumber() );

        return transactionDTO;
    }

    private Long transactionPayeeId(Transaction transaction) {
        if ( transaction == null ) {
            return null;
        }
        Payee payee = transaction.getPayee();
        if ( payee == null ) {
            return null;
        }
        Long id = payee.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
