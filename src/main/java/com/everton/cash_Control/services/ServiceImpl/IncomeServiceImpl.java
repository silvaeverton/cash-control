package com.everton.cash_Control.services.ServiceImpl;

import com.everton.cash_Control.dtos.RequestIncomeDto;
import com.everton.cash_Control.dtos.ResponseIncomeDto;
import com.everton.cash_Control.entities.CashRegister;
import com.everton.cash_Control.entities.Income;
import com.everton.cash_Control.entities.User;
import com.everton.cash_Control.enums.PaymentMethod;
import com.everton.cash_Control.exceptions.custom.NotFoundException;
import com.everton.cash_Control.repositories.CashRegisterRepository;
import com.everton.cash_Control.repositories.IncomeRepository;
import com.everton.cash_Control.services.CashRegisterService;
import com.everton.cash_Control.services.IncomeService;
import com.everton.cash_Control.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {

    private final CashRegisterRepository cashRegisterRepository;

    private final CashRegisterService cashRegisterService;

    private final IncomeRepository incomeRepository;

    private final UserService userService;

    @Override
    public Income createIncome(RequestIncomeDto request) {

        Income income = new Income();

        User user = userService.getUserById(request.getUserId());

        user.setId(request.getUserId());
        income.setAmount(request.getAmount());
        income.setDate(request.getDate());
        income.setDescription(request.getDescription());
        income.setPaymentMethod(request.getPaymentMethod());
        income.setUser(user);

        return incomeRepository.saveAndFlush(income);
    }

    @Override
    public ResponseIncomeDto findIncomeById(Long idIncome) {

        Income income = incomeRepository.findById(idIncome).orElseThrow(()->
                new NotFoundException("Income not Found", 404));

        ResponseIncomeDto dto = new ResponseIncomeDto();
        dto.setDate(income.getDate());
        dto.setAmount(income.getAmount());
        dto.setDescription(income.getDescription());
        dto.setPaymentMethod(income.getPaymentMethod());

        return dto;
    }

    @Override
    public List<ResponseIncomeDto> findAllIncomes() {

        List<Income> listIncome = incomeRepository.findAll();

        if(listIncome.isEmpty()) {
            throw new NotFoundException("The Income List not Found",404);
        }

        return   listIncome.stream().map(income -> {
            ResponseIncomeDto dto = new ResponseIncomeDto();
            dto.setId(income.getId());
            dto.setUserId(income.getId());
            dto.setDate(income.getDate());
            dto.setAmount(income.getAmount());
            dto.setDescription(income.getDescription());
            dto.setPaymentMethod(income.getPaymentMethod());

            return dto;


        }).toList();

    }

    @Override
    public List<ResponseIncomeDto> findIncomeByUser(Long idUser) {
        userService.getUserById(idUser);

        List<Income> list = incomeRepository.findByUser_id(idUser);

        return   list.stream().map(income -> {
            ResponseIncomeDto dto = new ResponseIncomeDto();
            dto.setUserId(income.getUser().getId());
            dto.setId(income.getId());
            dto.setDate(income.getDate());
            dto.setAmount(income.getAmount());
            dto.setDescription(income.getDescription());
            dto.setPaymentMethod(income.getPaymentMethod());
            return dto;

        }).toList();

    }

    @Override
    public List<ResponseIncomeDto> findIncomeByPayment(PaymentMethod paymentMethod) {

        List<Income> list = incomeRepository.findByPaymentMethod(paymentMethod);

        return list.stream().map(income -> {
            ResponseIncomeDto dto = new ResponseIncomeDto();
            dto.setId(income.getId());
            dto.setDate(income.getDate());
            dto.setAmount(income.getAmount());
            dto.setUserId(income.getUser().getId());
            dto.setDescription(income.getDescription());
            dto.setPaymentMethod(income.getPaymentMethod());
            return dto;

        }).toList();
    }

    @Override
    public List<ResponseIncomeDto> findIncomeByMonth(int year, int month) {
        LocalDate start = LocalDate.of(year,month,1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        List<Income> incomes = incomeRepository.findByDateBetween(start,end);

        return incomes.stream().map(income -> {
            ResponseIncomeDto dto = new ResponseIncomeDto();
            dto.setId(income.getId());
            dto.setDate(income.getDate());
            dto.setAmount(income.getAmount());
            dto.setUserId(income.getUser().getId());
            dto.setDescription(income.getDescription());
            dto.setPaymentMethod(income.getPaymentMethod());
            return dto;

        }).toList();

    }

    @Override
    public void deleteIncomerById(Long idIncomer) {

        Income income = incomeRepository.findById(idIncomer).orElseThrow(()->
                new NotFoundException("Income not found", 404));

        CashRegister cash = cashRegisterService.getCashRegister();
        cash.setBalance(cash.getBalance().subtract(income.getAmount()));

        cashRegisterRepository.saveAndFlush(cash);

        incomeRepository.delete(income);

    }


}










