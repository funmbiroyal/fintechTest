package com.challenge.fintech.repository;

import com.challenge.fintech.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface LoanRepository extends JpaRepository<Loan, Long> {
        List<Loan> findByUserId(Long userId);
    }

