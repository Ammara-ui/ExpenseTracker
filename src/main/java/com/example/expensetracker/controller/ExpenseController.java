package com.example.expensetracker.controller;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    public String getAllExpenses(Model model) {
        model.addAttribute("expenses", expenseService.getAllExpenses());
        return "expense/list";
    }

    @GetMapping("/{id}")
    public String getExpenseById(@PathVariable Long id, Model model) {
        Expense expense = expenseService.getExpenseById(id);
        model.addAttribute("expense", expense);
        return "expense/view";
    }

    @GetMapping("/new")
    public String createExpenseForm(Model model) {
        model.addAttribute("expense", new Expense());
        return "expense/form";
    }

    @PostMapping("/new")
    public String createExpense(@ModelAttribute Expense expense) {
        expenseService.saveExpense(expense);
        return "redirect:/expenses";
    }

    @GetMapping("/edit/{id}")
    public String editExpenseForm(@PathVariable Long id, Model model) {
        Expense expense = expenseService.getExpenseById(id);
        model.addAttribute("expense", expense);
        return "expense/form";
    }

    @PostMapping("/edit/{id}")
    public String editExpense(@PathVariable Long id, @ModelAttribute Expense updatedExpense) {
        Optional<Expense> existingExpense = Optional.ofNullable(expenseService.getExpenseById(id));
        existingExpense.ifPresent(exp -> {
            exp.setDescription(updatedExpense.getDescription());
            exp.setAmount(updatedExpense.getAmount());
            expenseService.saveExpense(exp);
        });
        return "redirect:/expenses";
    }

    @GetMapping("/delete/{id}")
    public String deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return "redirect:/expenses";
    }
}
