package com.augusto.smartbudget.domain.repository;

import com.augusto.smartbudget.domain.entity.Expense;
import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository {
    Expense save(Expense expense);
    List<Expense> findAll();
    List<Expense> findByDateBetween(LocalDate start, LocalDate end);
}
