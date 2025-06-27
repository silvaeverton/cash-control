package com.everton.cash_Control.controllers;

import com.everton.cash_Control.dtos.ExitCategorySummaryDTO;
import com.everton.cash_Control.dtos.RequestExpenseDto;
import com.everton.cash_Control.dtos.ResponseExpenseDto;
import com.everton.cash_Control.entities.Expense;
import com.everton.cash_Control.enums.ExitCategory;
import com.everton.cash_Control.enums.PaymentMethod;
import com.everton.cash_Control.services.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Expense createExpense(@RequestBody RequestExpenseDto request) {
        return expenseService.createExpense(request);
    }

    @GetMapping("/{idExpense}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseExpenseDto findExpenseById(@PathVariable("idExpense") Long idExpense) {
        return expenseService.findExpenseById(idExpense);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseExpenseDto> allExpense() {
        return expenseService.allExpense();
    }

    @GetMapping("/by-month")
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseExpenseDto> findExpenseByMonth(@RequestParam int year,@RequestParam int month) {
        return expenseService.findExpenseByMonth(year, month);
    }

    @GetMapping("/user/{idUser}")
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseExpenseDto> findExpenseByUser(@PathVariable("idUser") Long idUser) {
        return expenseService.findExpenseByUser(idUser);
    }

    @GetMapping("/payment")
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseExpenseDto> findExpenseByPayment(@RequestParam("method") PaymentMethod payment) {
        return expenseService.findExpenseByPayment(payment);
    }

    @GetMapping("/category")
    @ResponseStatus(HttpStatus.OK)
    public List<ExitCategorySummaryDTO> groupExitsByCategory(@RequestParam ExitCategory category) {
        return expenseService.groupExitsByCategory(category);
    }

    @DeleteMapping("/{idExpense}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExpense(@PathVariable Long idExpense) {
        expenseService.deleteExpense(idExpense);
    }

}
