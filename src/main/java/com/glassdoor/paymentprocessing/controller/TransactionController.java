package com.glassdoor.paymentprocessing.controller;

import com.glassdoor.paymentprocessing.api.v1.TransactionMapper;
import com.glassdoor.paymentprocessing.domain.Payee;
import com.glassdoor.paymentprocessing.domain.Transaction;
import com.glassdoor.paymentprocessing.model.TransactionDTO;
import com.glassdoor.paymentprocessing.model.TransactionVerificationDTO;
import com.glassdoor.paymentprocessing.repositories.PayeeRepository;
import com.glassdoor.paymentprocessing.repositories.TransactionRepository;
import com.glassdoor.paymentprocessing.services.TransactionService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.exceptions.TemplateInputException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Main Controller that exposes the API that checks the transaction before hand
 */

@Slf4j
@Controller
@RepositoryRestController
@RequestMapping(path = "/transactions")
public class TransactionController implements WebMvcConfigurer {

    private final TransactionMapper transactionMapper;
    private final TransactionRepository transactionRepository;
    private final PayeeRepository payeeRepository;
    private final TransactionService transactionService;

    public TransactionController(TransactionRepository transactionRepository, PayeeRepository payeeRepository, TransactionService transactionService) {
        this.transactionRepository = transactionRepository;
        this.payeeRepository = payeeRepository;
        this.transactionService = transactionService;
        this.transactionMapper = TransactionMapper.INSTANCE;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    /**
     * Method that initiates the view which starts the transaction.
     */

    @GetMapping("/checkTransaction")
    public String initiateTransaction(Model model) {
        model.addAttribute("transactionVerification", new TransactionVerificationDTO());
        return "cardForm";
    }

    /**
     * Method that submits transaction.
     * Initially checks the username, then the billing address, as specified in the document.
     * Finally, adds the transaction to the table if all the cases pass.
     */

    @PostMapping("/checkTransaction")
    public String submitTransaction(Model model, @ModelAttribute TransactionVerificationDTO transactionVerification) {
        String transactionSuccess = "Transaction Failed as the username does not exist.";
        log.info(String.valueOf(transactionVerification));
        Payee user = transactionService.findPayee(transactionVerification.getUserName());
        if (user == null) {
            transactionSuccess = "Transaction Failed due to address mismatch.";
        } else if(transactionService.verifyTransaction(transactionVerification, user)) {
            transactionService.insertTransaction(transactionVerification, user);
            log.info("Inserted Transaction");
            transactionSuccess = "Transaction Successful. Proceeding to the Payment Gateway! ";
        }
        model.addAttribute("result", transactionSuccess);
        return "result";
    }

//    @GetMapping("/test")
//    public String test() {
//        return "ok!";
//    }

    @GetMapping
    @ResponseBody
    public List<TransactionDTO> getAllTransactions() {
        log.info("Fetching all transactions...");

        return transactionRepository.findAll().stream()
                .map(transactionMapper::transactionToTransactionDto)
                .collect(Collectors.toList());
    }

    @PostMapping({"/fetchAll"})
    @ResponseBody
    public Transaction createTransaction(@RequestBody TransactionDTO transactionDTO) {
        Transaction transaction = transactionMapper.transactionDtoToTransaction(transactionDTO);
        Payee payee = payeeRepository.findById(transactionDTO.getPayeeId()).orElseThrow(ResourceNotFoundException::new);
        transaction.setPayee(payee);
        return transactionRepository.save(transaction);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class, TemplateInputException.class})
    public String handleNotFound(Exception exception, Model model) {
        log.debug("Handling a page not found exception!");
        log.error(exception.getMessage());
        return "404error";
    }

}
