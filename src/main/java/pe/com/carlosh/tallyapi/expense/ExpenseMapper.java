package pe.com.carlosh.tallyapi.expense;

import pe.com.carlosh.tallyapi.expense.dto.ExpenseResponseDTO;

public class ExpenseMapper {
    public static ExpenseResponseDTO toResponse(Expense expense) {
        return new ExpenseResponseDTO(
                expense.getId(),
                expense.getAmount(),
                expense.getDescription(),
                expense.getExpenseDate(),
                expense.getCategory().getName(),
                expense.getCategory().getId(),
                expense.getBudget() != null ? expense.getBudget().getId() : null,
                expense.getBudget() != null ? expense.getBudget().getName() : null
        );
    }
}