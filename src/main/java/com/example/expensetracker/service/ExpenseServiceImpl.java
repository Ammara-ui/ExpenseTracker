package com.example.expensetracker.service;

import com.example.expensetracker.model.Expense;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final List<Expense> expenses = new ArrayList<>();

    @Override
    public Iterable<Expense> getAllExpenses() {
        return expenses;
    }

    @Override
    public Expense getExpenseById(Long id) {
        return expenses.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Expense saveExpense(Expense expense) {
        if (expense.getId() == null) {
            expense.setId(generateId());
            expenses.add(expense);
        } else {
            Expense existingExpense = getExpenseById(expense.getId());
            if (existingExpense != null) {
                existingExpense.setDescription(expense.getDescription());
                existingExpense.setAmount(expense.getAmount());
            }
        }
        return expense;
    }

    @Override
    public void deleteExpense(Long id) {
        expenses.removeIf(e -> e.getId().equals(id));
    }

    private Long generateId() {
        return System.currentTimeMillis();
    }
}
