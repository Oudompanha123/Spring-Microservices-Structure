package org.example.expenseservice.controller;


import org.example.expenseservice.entity.Expense;
import org.example.expenseservice.entity.dtos.ApiResponseDto;
import org.example.expenseservice.entity.dtos.ExpenseRequestDto;
import org.example.expenseservice.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/new")
    public ResponseEntity<ApiResponseDto<?>> addExpense(@RequestBody ExpenseRequestDto expenseRequestDto) {
        return expenseService.addExpense(expenseRequestDto);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponseDto<?>> getAllExpense(){
        return expenseService.getAllExpenses();
    }

}
