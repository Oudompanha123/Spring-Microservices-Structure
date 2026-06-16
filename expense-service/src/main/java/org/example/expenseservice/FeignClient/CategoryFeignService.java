package org.example.expenseservice.FeignClient;

import org.example.expenseservice.entity.dtos.ApiResponseDto;
import org.example.expenseservice.entity.dtos.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("CATEGORY-SERVICE")
public interface CategoryFeignService {

    @GetMapping("/category/{id}")
    ResponseEntity<ApiResponseDto<Category>> getCategoryById(@PathVariable("id") String id);
}
