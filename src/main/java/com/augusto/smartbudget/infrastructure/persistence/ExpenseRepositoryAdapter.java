package com.augusto.smartbudget.infrastructure.persistence;

import com.augusto.smartbudget.domain.entity.Expense;
import com.augusto.smartbudget.domain.repository.ExpenseRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ExpenseRepositoryAdapter implements ExpenseRepository {

    private final JpaExpenseRepository repository;

    public ExpenseRepositoryAdapter(JpaExpenseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Expense save(Expense expense) {
        return repository.save(expense);
    }

    @Override
    public List<Expense> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Expense> findByDateBetween(LocalDate start, LocalDate end) {
        return repository.findByDateBetween(start, end);
    }
}
