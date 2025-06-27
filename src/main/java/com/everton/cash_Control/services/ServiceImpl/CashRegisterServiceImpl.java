package com.everton.cash_Control.services.ServiceImpl;

import com.everton.cash_Control.dtos.CashFlowDto;
import com.everton.cash_Control.dtos.CashFlowResponseDto;
import com.everton.cash_Control.dtos.DailySummaryDTO;
import com.everton.cash_Control.entities.CashRegister;
import com.everton.cash_Control.entities.Expense;
import com.everton.cash_Control.entities.Income;
import com.everton.cash_Control.repositories.CashRegisterRepository;
import com.everton.cash_Control.repositories.ExpenseRepository;
import com.everton.cash_Control.repositories.IncomeRepository;
import com.everton.cash_Control.services.CashRegisterService;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
public class CashRegisterServiceImpl implements CashRegisterService {

    private final CashRegisterRepository cashRegisterRepository;

    private final ExpenseRepository expenseRepository;

    private final IncomeRepository incomeRepository;


    @Override
    public BigDecimal getBalance() {
        CashRegister cash = cashRegisterRepository.findById(1L).orElseGet(()->
               cashRegisterRepository.saveAndFlush(new CashRegister()));

        return cash.getBalance();
    }

    @Override
    public CashFlowResponseDto getCashFlowByPeriodWithTotals(LocalDate startDate, LocalDate endDate) {

        List<Income> incomes = incomeRepository.findIncomeByMonth(startDate, endDate);
        List<Expense> expenses = expenseRepository.findExpenseByMonth(startDate,endDate);

        List<CashFlowDto> cashFlowDtos = new ArrayList<>();

        BigDecimal totalIncomes = BigDecimal.ZERO;
        BigDecimal totalExpense = BigDecimal.ZERO;

        for (Income income : incomes) {
            CashFlowDto dto = new CashFlowDto();
            dto.setType("Entry");
            dto.setDate(income.getDate());
            dto.setValue(income.getAmount());
            dto.setDescription(income.getDescription());
            dto.setPaymentMethod(income.getPaymentMethod());
            cashFlowDtos.add(dto);

            totalIncomes = totalIncomes.add(income.getAmount());
        }

        for (Expense expense : expenses) {
            CashFlowDto dto = new CashFlowDto();
            dto.setType("Exit");
            dto.setDate(expense.getDate());
            dto.setValue(expense.getAmount());
            dto.setDescription(expense.getDescription());
            dto.setPaymentMethod(expense.getPaymentMethod());
            cashFlowDtos.add(dto);

            totalExpense = totalExpense.add(expense.getAmount());
        }

        cashFlowDtos.sort(Comparator.comparing(CashFlowDto::getDate));

        CashFlowResponseDto response = new CashFlowResponseDto();
        response.setCashFlowList(cashFlowDtos);
        response.setTotalEntries(totalIncomes);
        response.setTotalExits(totalExpense);
        response.setBalance(totalIncomes.subtract(totalExpense));

        return response;
    }

    @Override
    public CashRegister getCashRegister() {

        return cashRegisterRepository.findById(1L).orElseGet(()-> {
            CashRegister cash = new CashRegister();
            cash.setId(1L);
            cash.setBalance(BigDecimal.ZERO);
            return cashRegisterRepository.saveAndFlush(cash);
        });


    }

    @Override
    public DailySummaryDTO getDalySummer(LocalDate date) {

        List<Income> incomes = incomeRepository.findIncomeByDate(date);
        List<Expense> expenses = expenseRepository.findExpenseByDate(date);

        BigDecimal totalEntries = incomes.stream().map(Income::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalExits = expenses.stream().map(Expense::getAmount).reduce(BigDecimal.ZERO,BigDecimal::add);
        BigDecimal totalBalance = totalEntries.subtract(totalExits);

        DailySummaryDTO dailySummaryDTO = new DailySummaryDTO();
        dailySummaryDTO.setTotalIncomer(totalEntries);
        dailySummaryDTO.setTotalExpense(totalExits);
        dailySummaryDTO.setTotalBalance(totalBalance);

        return dailySummaryDTO;
    }
}
