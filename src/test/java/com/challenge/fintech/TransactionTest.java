package com.challenge.fintech;

import com.challenge.fintech.model.Loan;
import com.challenge.fintech.model.Transaction;
import com.challenge.fintech.repository.LoanRepository;
import com.challenge.fintech.repository.TransactionRepository;
import com.challenge.fintech.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private TransactionService transactionService;

    private Loan loan;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Prepare Loan object
        loan = new Loan();
        loan.setId(1L);
        loan.setLoanAmount(20000.0);
        loan.setStatus("approved");

        // Prepare Transaction object
        transaction = new Transaction();
        transaction.setAmount(5000.0);
        transaction.setType("repayment");
        transaction.setLoan(loan);
    }

    @Test
    void testRecordTransaction_Success() {
        // Given
        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(invocation -> invocation.getArguments()[0]);

        // When
        Transaction savedTransaction = transactionService.recordTransaction(1L, 5000.0, "repayment");

        // Then
        assertNotNull(savedTransaction);
        assertEquals(5000.0, savedTransaction.getAmount());
        assertEquals("repayment", savedTransaction.getType());
        assertEquals(loan, savedTransaction.getLoan());

        verify(loanRepository, times(1)).findById(1L);
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    void testRecordTransaction_LoanNotFound() {
        // Given
        when(loanRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            transactionService.recordTransaction(1L, 5000.0, "repayment");
        });

        assertEquals("Loan not found", exception.getMessage());

        verify(loanRepository, times(1)).findById(1L);
        verify(transactionRepository, times(0)).save(any(Transaction.class)); // Ensure save is not called
    }

    @Test
    void testGetTransactionsByLoan() {
        // Given
        Transaction transaction1 = new Transaction(null, 5000.0, "repayment", loan, null);
        Transaction transaction2 = new Transaction(null, 10000.0, "repayment", loan, null);
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2);

        when(transactionRepository.findByLoanId(1L)).thenReturn(transactions);

        // When
        List<Transaction> result = transactionService.getTransactionsByLoan(1L);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(5000.0, result.get(0).getAmount());
        assertEquals(10000.0, result.get(1).getAmount());

        verify(transactionRepository, times(1)).findByLoanId(1L);
    }
}
