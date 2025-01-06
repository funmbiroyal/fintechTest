package com.challenge.fintech;

import com.challenge.fintech.dto.LoanRequest;
import com.challenge.fintech.model.Loan;
import com.challenge.fintech.model.User;
import com.challenge.fintech.repository.LoanRepository;
import com.challenge.fintech.repository.UserRepository;
import com.challenge.fintech.service.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoanServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanService loanService;

    private LoanRequest loanRequest;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Prepare the LoanRequest and User objects for the test
        loanRequest = new LoanRequest();
        loanRequest.setUserId(1L);
        loanRequest.setLoanAmount(20000.0);
        loanRequest.setTenure(12);
        loanRequest.setInterestRate(5.0);

        user = new User();
        user.setId(1L);
        user.setFullName("John Doe");
    }

    @Test
    void testApplyForLoan_Success() {
        // Given
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
        when(loanRepository.save(any(Loan.class))).thenAnswer(invocation -> invocation.getArguments()[0]);

        // When
        Loan loan = loanService.applyForLoan(loanRequest);

        // Then
        assertNotNull(loan);
        assertEquals(20000.0, loan.getLoanAmount());
        assertEquals(12, loan.getTenure());
        assertEquals(5.0, loan.getInterestRate());
        assertEquals("pending", loan.getStatus()); // Default status
        assertEquals(user, loan.getUser()); // Check if the user was correctly set

        verify(userRepository, times(1)).findById(1L);
        verify(loanRepository, times(1)).save(any(Loan.class));
    }

    @Test
    void testApplyForLoan_UserNotFound() {
        // Given
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            loanService.applyForLoan(loanRequest);
        });

        assertEquals("User not found", exception.getMessage());

        verify(userRepository, times(1)).findById(1L);
        verify(loanRepository, times(0)).save(any(Loan.class)); // Ensure save is not called
    }
}
