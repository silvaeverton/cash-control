package com.everton.cash_Control.services;

import com.everton.cash_Control.dtos.ExitCategorySummaryDTO;
import com.everton.cash_Control.dtos.RequestExpenseDto;
import com.everton.cash_Control.dtos.ResponseExpenseDto;
import com.everton.cash_Control.entities.Expense;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpenseService {

    public Expense createExpense(RequestExpenseDto request);
    public ResponseExpenseDto findExpenseById(Long idExpense);
    public List<ResponseExpenseDto> allExpense();
    public List<ResponseExpenseDto> findExpenseByMonth(String month);
    public List<ResponseExpenseDto> findExpenseByUser(Long idUser);
    public List<ResponseExpenseDto> findExpenseByPayment(String payment);
    public void deleteExpense(Long idExpense);
    public List<ExitCategorySummaryDTO> groupExitsByCategory(String category);
}
