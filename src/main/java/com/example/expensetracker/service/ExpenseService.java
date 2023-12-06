package com.example.expensetracker.service;

import com.example.expensetracker.model.Expense;

public interface ExpenseService {
    Iterable<Expense> getAllExpenses();
    Expense getExpenseById(Long id);
    Expense saveExpense(Expense expense);
    void deleteExpense(Long id);
}
