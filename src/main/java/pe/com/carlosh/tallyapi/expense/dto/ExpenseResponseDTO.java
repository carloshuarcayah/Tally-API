package pe.com.carlosh.tallyapi.expense.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseResponseDTO(
        Long id,
        BigDecimal amount,
        String description,
        LocalDate expenseDate,
        String categoryName,
        Long categoryId,
        Long budgetId,
        String budgetName
) {}