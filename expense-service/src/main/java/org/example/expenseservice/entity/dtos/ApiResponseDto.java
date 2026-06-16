package org.example.expenseservice.entity.dtos;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ApiResponseDto <T>{
    private boolean isSuccess;
    private String message;
    private T response;
}
