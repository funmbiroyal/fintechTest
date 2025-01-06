package com.challenge.fintech.controller;

import com.challenge.fintech.model.Transaction;
import com.challenge.fintech.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController{

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/record")
    public ResponseEntity<Transaction> recordTransaction(@RequestParam Long loanId, @RequestParam Double amount, @RequestParam String type) {
        return ResponseEntity.ok(transactionService.recordTransaction(loanId, amount, type));
    }

    @GetMapping("/loan")
    public ResponseEntity<List<Transaction>> getTransactionsByLoan(@RequestParam Long loanId) {
        return ResponseEntity.ok(transactionService.getTransactionsByLoan(loanId));
    }
}