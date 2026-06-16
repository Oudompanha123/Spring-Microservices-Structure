package org.example.expenseservice.service;


import org.example.expenseservice.entity.dtos.ApiResponseDto;
import org.example.expenseservice.entity.dtos.ExpenseRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ExpenseService {
    ResponseEntity<ApiResponseDto<?>> addExpense(ExpenseRequestDto requestDto);
    ResponseEntity<ApiResponseDto<?>> getAllExpenses();
}
