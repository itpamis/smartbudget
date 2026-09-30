package com.augusto.smartbudget.application.usecase;

import com.augusto.smartbudget.domain.entity.Expense;
import com.augusto.smartbudget.domain.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class CreateExpenseUseCase {

    private final ExpenseRepository repository;

    public CreateExpenseUseCase(ExpenseRepository repository) {
        this.repository = repository;
    }

    public Expense execute(String description, BigDecimal amount, String category, LocalDate date) {
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero.");
        }

        return repository.save(new Expense(description, amount, category, date));
    }
}
