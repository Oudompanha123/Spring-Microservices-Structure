package org.example.expenseservice.Repository;

import org.example.expenseservice.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, String> {

}
