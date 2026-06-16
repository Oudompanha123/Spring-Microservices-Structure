package org.example.expenseservice.service;


import org.example.expenseservice.FeignClient.CategoryFeignService;
import org.example.expenseservice.Repository.ExpenseRepository;
import org.example.expenseservice.entity.Expense;
import org.example.expenseservice.entity.dtos.ApiResponseDto;
import org.example.expenseservice.entity.dtos.Category;
import org.example.expenseservice.entity.dtos.ExpenseRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    private final CategoryFeignService categoryFeignService;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, CategoryFeignService categoryFeignService) {
        this.expenseRepository = expenseRepository;
        this.categoryFeignService = categoryFeignService;
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> addExpense(ExpenseRequestDto requestDto) {
        try {

            // fetching category from category service
            Category category = Objects.requireNonNull(categoryFeignService.getCategoryById(requestDto.getCategoryId())
                    .getBody())
                    .getResponse();

            if (category == null) {
                // Try to create a custom exception and handle them using exception handlers
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        ApiResponseDto.builder()
                                .isSuccess(false)
                                .response("Category not exists with id: " + requestDto.getCategoryId())
                                .message("Unable to create Category.")
                                .build()
                );
            }

            Expense expense = Expense.builder()
                    .description(requestDto.getDescription())
                    .amount(requestDto.getAmount())
                    .date(requestDto.getDate())
                    .categoryId(requestDto.getCategoryId())
                    .build();

            expenseRepository.save(expense);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    ApiResponseDto.builder()
                            .isSuccess(true)
                            .message("Expense saved successfully!")
                            .build()
            );

        }catch (Exception e) {
//            Try to create a custom exception and handle them using exception handlers
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ApiResponseDto.builder()
                            .isSuccess(false)
                            .response("Unable to process right now. Try again later!")
                            .message("Unable to save expense.")
                            .build()
            );
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> getAllExpenses() {
        List<Expense> expenses = expenseRepository.findAll();
        try {
            return ResponseEntity.ok(
                    ApiResponseDto.builder()
                            .isSuccess(true)
                            .response(expenses)
                            .message(expenses.size() + " results found!")
                            .build()
            );
        }catch (Exception e) {
            // Try to create a custom exception and handle them using exception handlers
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ApiResponseDto.builder()
                            .isSuccess(false)
                            .response("Unable to process right now. Try again later!")
                            .message("No results found!")
                            .build()
            );
        }
    }
}
