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
    List<Expense> findExpenseByUserId(Long idUser);
    List<Expense> findExpenseByMonth(LocalDate startDate, LocalDate endDate);
    List<Expense> findExpenseByDate(LocalDate date);
    List<Expense> findExpenseByPayment(PaymentMethod paymentMethod);
    List<Expense> findExpenseByCategory(ExitCategory category);
}
