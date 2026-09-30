package com.augusto.smartbudget.interfaces.controller;

import com.augusto.smartbudget.application.usecase.CreateExpenseUseCase;
import com.augusto.smartbudget.application.usecase.ListExpensesUseCase;
import com.augusto.smartbudget.domain.entity.Expense;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final CreateExpenseUseCase createExpenseUseCase;
    private final ListExpensesUseCase listExpensesUseCase;

    public ExpenseController(CreateExpenseUseCase createExpenseUseCase,
                              ListExpensesUseCase listExpensesUseCase) {
        this.createExpenseUseCase = createExpenseUseCase;
        this.listExpensesUseCase = listExpensesUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Expense create(@Valid @RequestBody CreateExpenseRequest request) {
        return createExpenseUseCase.execute(
                request.description(),
                request.amount(),
                request.category(),
                request.date()
        );
    }

    @GetMapping
    public List<Expense> list() {
        return listExpensesUseCase.all();
    }

    public record CreateExpenseRequest(
            @NotBlank String description,
            @NotNull @DecimalMin("0.01") BigDecimal amount,
            @NotBlank String category,
            @NotNull LocalDate date
    ) {}
}
