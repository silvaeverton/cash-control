package com.everton.cash_Control.services.ServiceImpl;

import com.everton.cash_Control.dtos.ExitCategorySummaryDTO;
import com.everton.cash_Control.dtos.RequestExpenseDto;
import com.everton.cash_Control.dtos.ResponseExpenseDto;
import com.everton.cash_Control.entities.CashRegister;
import com.everton.cash_Control.entities.Expense;
import com.everton.cash_Control.entities.User;
import com.everton.cash_Control.enums.ExitCategory;
import com.everton.cash_Control.enums.PaymentMethod;
import com.everton.cash_Control.exceptions.custom.NotFoundException;
import com.everton.cash_Control.repositories.CashRegisterRepository;
import com.everton.cash_Control.repositories.ExpenseRepository;
import com.everton.cash_Control.services.CashRegisterService;
import com.everton.cash_Control.services.ExpenseService;
import com.everton.cash_Control.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final CashRegisterRepository cashRegisterRepository;

    private final CashRegisterService cashRegisterService;

    private final ExpenseRepository expenseRepository;

    private final UserService userService;

    @Override
    public Expense createExpense(RequestExpenseDto request) {

        Expense expense = new Expense();
        expense.setDate(request.getDate());
        expense.setAmount(request.getAmount());
        expense.setDescription(request.getDescription());
        expense.setExitCategory(request.getExitCategory());
        expense.setPaymentMethod(request.getPaymentMethod());

        User user = userService.getUserById(request.getUserId());
        expense.setUser(user);

        return expenseRepository.saveAndFlush(expense);

    }

    @Override
    public ResponseExpenseDto findExpenseById(Long idExpense) {

        Expense expense = expenseRepository.findById(idExpense).orElseThrow(()->
                new NotFoundException("Expense not found",404));

        ResponseExpenseDto dto = new ResponseExpenseDto();
        dto.setDate(expense.getDate());
        dto.setAmount(expense.getAmount());
        dto.setDescription(expense.getDescription());
        dto.setExitCategory(expense.getExitCategory());
        dto.setPaymentMethod(expense.getPaymentMethod());

        return dto;
    }

    @Override
    public List<ResponseExpenseDto> allExpense() {

        List<Expense> list = expenseRepository.findAll();

        if(list.isEmpty()) {
            throw new NotFoundException("Expense List Not Found!",404);
        }
        return list.stream().map(expense -> {
            ResponseExpenseDto dto = new ResponseExpenseDto();
            dto.setDate(expense.getDate());
            dto.setAmount(expense.getAmount());
            dto.setDescription(expense.getDescription());
            dto.setExitCategory(expense.getExitCategory());
            dto.setPaymentMethod(expense.getPaymentMethod());
            return dto;

        }).toList();

    }

    @Override
    public List<ResponseExpenseDto> findExpenseByMonth(int year, int month) {

        LocalDate start = LocalDate.of(year,month,1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        List<Expense>  list = expenseRepository.findByDateBetween(start,end);

        if(list.isEmpty()) {
            throw new NotFoundException("There are no expenses in the month ", 404);
        }

        return list.stream().map(expense -> {
            ResponseExpenseDto dto = new ResponseExpenseDto();
            dto.setDate(expense.getDate());
            dto.setAmount(expense.getAmount());
            dto.setDescription(expense.getDescription());
            dto.setExitCategory(expense.getExitCategory());
            dto.setPaymentMethod(expense.getPaymentMethod());
            return dto;

        }).toList();


    }

    @Override
    public List<ResponseExpenseDto> findExpenseByUser(Long userId) {

        List<Expense> list = expenseRepository.findByUser_id(userId);

        if(list.isEmpty()) {
            throw new NotFoundException("This user does not have an expense",404);
        }
        return list.stream().map(expense -> {
            ResponseExpenseDto dto = new ResponseExpenseDto();
            dto.setDate(expense.getDate());
            dto.setAmount(expense.getAmount());
            dto.setDescription(expense.getDescription());
            dto.setExitCategory(expense.getExitCategory());
            dto.setPaymentMethod(expense.getPaymentMethod());
            return dto;

        }).toList();


    }

    @Override
    public List<ResponseExpenseDto> findExpenseByPayment(PaymentMethod paymentMethod) {

        List<Expense> list = expenseRepository.findByPaymentMethod(paymentMethod);

        if(list.isEmpty()) {
            throw new NotFoundException("Not exist expense for this payment method",404);
        }
        return list.stream().map(expense -> {
            ResponseExpenseDto dto = new ResponseExpenseDto();
            dto.setDate(expense.getDate());
            dto.setAmount(expense.getAmount());
            dto.setDescription(expense.getDescription());
            dto.setExitCategory(expense.getExitCategory());
            dto.setPaymentMethod(expense.getPaymentMethod());
            return dto;

        }).toList();
    }

    @Override
    public void deleteExpense(Long idExpense) {

        Expense expense = expenseRepository.findById(idExpense).orElseThrow(()->
                new NotFoundException("Expense not found",404));


        CashRegister cash = cashRegisterService.getCashRegister();

        cash.setBalance(cash.getBalance().subtract(expense.getAmount()));

        cashRegisterRepository.saveAndFlush(cash);

        expenseRepository.delete(expense);

    }

    @Override
    public List<ExitCategorySummaryDTO> groupExitsByCategory(ExitCategory category) {

        List<Expense> list = expenseRepository. findByExitCategory( category);

        if(list.isEmpty()) {
            throw new NotFoundException("Not exist expense for this category",404);
        }
       return list.stream().map(expense -> {
            ExitCategorySummaryDTO dto = new ExitCategorySummaryDTO();
            dto.setAmount(expense.getAmount());
            dto.setDescription(expense.getDescription());
            dto.setExitCategory(expense.getExitCategory());
            return dto;

        }).toList();
    }
}
