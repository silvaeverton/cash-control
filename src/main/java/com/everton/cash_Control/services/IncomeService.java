package com.everton.cash_Control.services;

import com.everton.cash_Control.dtos.RequestIncomeDto;
import com.everton.cash_Control.dtos.ResponseIncomeDto;
import com.everton.cash_Control.entities.Income;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;

@Service
public interface IncomeService {

    public Income createIncome(RequestIncomeDto request);
    public ResponseIncomeDto findIncomeById(Long idIncome);
    public List<ResponseIncomeDto> findAllIncomes();
    public List<ResponseIncomeDto> findIncomeByUser(Long idUser);
    public List<ResponseIncomeDto> findIncomeByPayment(String payment);
    public List<ResponseIncomeDto> findIncomeByMonth(Spring month);
    public void deleteIncomerById(Long idIncomer);

}
