package com.challenge.fintech.service;

import com.challenge.fintech.dto.LoanRequest;
import com.challenge.fintech.model.Loan;
import com.challenge.fintech.model.User;
import com.challenge.fintech.repository.LoanRepository;
import com.challenge.fintech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoanRepository loanRepository;

    public Loan applyForLoan(LoanRequest loanRequest) {
        User user = userRepository.findById(loanRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Loan loan = new Loan();
        loan.setLoanAmount(loanRequest.getLoanAmount());
        loan.setTenure(loanRequest.getTenure());
        loan.setInterestRate(loanRequest.getInterestRate());
        loan.setStatus("pending");  // Default status if not provided
        loan.setUser(user);  // Set the user object

        // Save the loan and return it
        return loanRepository.save(loan);
    }


    public List<Loan> getLoansByUser(Long userId) {
        return loanRepository.findByUserId(userId);
    }

    public Loan updateLoanStatus(Long loanId, String status) {
        Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new RuntimeException("Loan not found"));
        loan.setStatus(status);
        return loanRepository.save(loan);
    }

    //TODO check the calculate interest rate again
    private double calculateInterestRate(Double loanAmount, Integer tenure) {
        // Example: dynamic interest rate calculation
        return loanAmount > 50000 ? 5.5 + tenure * 0.1 : 4.5 + tenure * 0.1;
    }
}
