package com.glassdoor.paymentprocessing.api.v1;

import com.glassdoor.paymentprocessing.domain.Transaction;
import com.glassdoor.paymentprocessing.model.TransactionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * It maps the Transaction object to the TansactionDTO object and vice versa.
 */

@Mapper
public interface TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    Transaction transactionDtoToTransaction(TransactionDTO transactionDTO);

    @Mapping(source = "payee.id", target = "payeeId")
    TransactionDTO transactionToTransactionDto(Transaction transaction);

}
