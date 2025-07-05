package com.everton.cash_Control.repositories;

import com.everton.cash_Control.entities.Expense;
import com.everton.cash_Control.enums.ExitCategory;
import com.everton.cash_Control.enums.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    List<Expense> findByUser_id(Long userId);
    List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);
    List<Expense> findExpenseByDate(LocalDate date);
    List<Expense> findByPaymentMethod(PaymentMethod paymentMethod);
    List<Expense>  findByExitCategory(ExitCategory category);
}
