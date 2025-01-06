package com.challenge.fintech.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Double amount;

    @NotBlank
    private String type; // e.g., "disbursement", "repayment"

    @ManyToOne
    @JoinColumn(name = "loan_id", nullable = false)
    private Loan loan;

    @CreationTimestamp
    private Date transactionDate;

    public void setId(Long id) {
        this.id = id;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
public Transaction(){

}

    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public Loan getLoan() {
        return loan;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public Transaction(Long id, Double amount, String type, Loan loan, Date transactionDate) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.loan = loan;
        this.transactionDate = transactionDate;
    }
}