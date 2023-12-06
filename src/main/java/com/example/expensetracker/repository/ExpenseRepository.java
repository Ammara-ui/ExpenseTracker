package com.example.expensetracker.repository;

import com.example.expensetracker.model.Expense;
import org.springframework.data.repository.CrudRepository;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {
}
