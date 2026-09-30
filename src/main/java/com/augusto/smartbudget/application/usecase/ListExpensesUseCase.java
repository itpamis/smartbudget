package com.augusto.smartbudget.application.usecase;

import com.augusto.smartbudget.domain.entity.Expense;
import com.augusto.smartbudget.domain.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ListExpensesUseCase {

    private final ExpenseRepository repository;

    public ListExpensesUseCase(ExpenseRepository repository) {
        this.repository = repository;
    }

    public List<Expense> all() {
        return repository.findAll();
    }

    public List<Expense> between(LocalDate start, LocalDate end) {
        return repository.findByDateBetween(start, end);
    }
}
