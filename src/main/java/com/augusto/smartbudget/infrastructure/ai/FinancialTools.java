package com.augusto.smartbudget.infrastructure.ai;

import com.augusto.smartbudget.application.usecase.ListExpensesUseCase;
import com.augusto.smartbudget.domain.entity.Expense;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class FinancialTools {

    private final ListExpensesUseCase listExpensesUseCase;

    public FinancialTools(ListExpensesUseCase listExpensesUseCase) {
        this.listExpensesUseCase = listExpensesUseCase;
    }

    @Tool(description = """
            Consulta os gastos reais registrados no banco de dados.

            Use esta ferramenta sempre que o usuário perguntar quanto gastou,
            quais foram seus gastos ou despesas.

            Os parâmetros devem estar obrigatoriamente no formato:
            yyyy-MM-dd

            Exemplo:
            inicio = 2026-09-01
            fim = 2026-09-30

            Nunca invente valores financeiros.
            O resultado desta ferramenta é a fonte oficial dos dados.
            """)
    public String consultarGastos(String inicio, String fim) {

        LocalDate dataInicio = LocalDate.parse(inicio);
        LocalDate dataFim = LocalDate.parse(fim);

        List<Expense> expenses =
                listExpensesUseCase.between(dataInicio, dataFim);

        BigDecimal total = expenses.stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        String gastos = expenses.stream()
                .map(e -> e.getDate()
                        + " - "
                        + e.getCategory()
                        + " - R$ "
                        + e.getAmount())
                .toList()
                .toString();

        return "DADOS OFICIAIS DO BANCO: "
                + "Quantidade de gastos: " + expenses.size()
                + ". Total exato: R$ " + total
                + ". Gastos registrados: " + gastos;
    }
}