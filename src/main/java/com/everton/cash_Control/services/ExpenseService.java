package com.everton.cash_Control.services;

import com.everton.cash_Control.dtos.ExitCategorySummaryDTO;
import com.everton.cash_Control.dtos.RequestExpenseDto;
import com.everton.cash_Control.dtos.ResponseExpenseDto;
import com.everton.cash_Control.entities.Expense;
import com.everton.cash_Control.enums.ExitCategory;
import com.everton.cash_Control.enums.PaymentMethod;

import java.util.List;


public interface ExpenseService {

    public Expense createExpense(RequestExpenseDto request);
    public ResponseExpenseDto findExpenseById(Long idExpense);
    public List<ResponseExpenseDto> allExpense();
    public List<ResponseExpenseDto> findExpenseByMonth(int year, int month);
    public List<ResponseExpenseDto> findExpenseByUser(Long idUser);
    public List<ResponseExpenseDto> findExpenseByPayment(PaymentMethod payment);
    public void deleteExpense(Long idExpense);
    public List<ExitCategorySummaryDTO> groupExitsByCategory(ExitCategory category);
}
