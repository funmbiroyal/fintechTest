package com.challenge.fintech.service;

import com.challenge.fintech.model.Loan;
import com.challenge.fintech.model.Transaction;
import com.challenge.fintech.repository.LoanRepository;
import com.challenge.fintech.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final LoanRepository loanRepository;

    public TransactionService(TransactionRepository transactionRepository, LoanRepository loanRepository) {
        this.transactionRepository = transactionRepository;
        this.loanRepository = loanRepository;
    }

    public Transaction recordTransaction(Long loanId, Double amount, String type) {
        Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new RuntimeException("Loan not found"));

        Transaction transaction = new Transaction(null, amount, type, loan, null);
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsByLoan(Long loanId) {
        return transactionRepository.findByLoanId(loanId);
    }
}
