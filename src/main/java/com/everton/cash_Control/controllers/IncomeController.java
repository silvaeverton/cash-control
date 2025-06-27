package com.everton.cash_Control.controllers;

import com.everton.cash_Control.dtos.RequestIncomeDto;
import com.everton.cash_Control.dtos.ResponseIncomeDto;
import com.everton.cash_Control.entities.Income;
import com.everton.cash_Control.enums.PaymentMethod;
import com.everton.cash_Control.services.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/income")
public class IncomeController {

    private final IncomeService incomeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Income createIncome(@RequestBody RequestIncomeDto request) {
        return incomeService.createIncome(request);
    }

    @GetMapping("/{idIncome}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseIncomeDto findIncomeById(@PathVariable("idIncome") Long idIncome) {
        return incomeService.findIncomeById(idIncome);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseIncomeDto> findAllIncomes() {
        return incomeService.findAllIncomes();
    }

    @GetMapping("/user/{idUser}")
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseIncomeDto> findIncomeByUser(@PathVariable("idUser") Long idUser) {
        return incomeService.findIncomeByUser(idUser);
    }

    @GetMapping("/payment")
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseIncomeDto> findIncomeByPayment(@RequestParam("method") PaymentMethod payment) {
        return incomeService.findIncomeByPayment(payment);
    }

    @GetMapping("/by-month")
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseIncomeDto> findIncomeByMonth(@RequestParam int year,@RequestParam int month) {
        return incomeService.findIncomeByMonth(year, month);
    }

    @DeleteMapping("/{idIncome}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIncomerById(@PathVariable("idIncome") Long idIncomer) {
        incomeService.deleteIncomerById(idIncomer);
    }
}
