package com.challenge.fintech.controller;
import com.challenge.fintech.dto.LoanRequest;
import com.challenge.fintech.model.Loan;
import com.challenge.fintech.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }
    @PostMapping("/apply")
    public Loan applyForLoan(@RequestBody LoanRequest loanRequest) {
        // Call the service method to apply for a loan
        return loanService.applyForLoan(loanRequest);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Loan>> getLoansByUser(@RequestParam Long userId) {
        return ResponseEntity.ok(loanService.getLoansByUser(userId));
    }

    @PutMapping("/updateStatus/")
    public ResponseEntity<Loan> updateLoanStatus(@RequestParam Long loanId, @RequestParam String status) {
        return ResponseEntity.ok(loanService.updateLoanStatus(loanId, status));
    }
}

