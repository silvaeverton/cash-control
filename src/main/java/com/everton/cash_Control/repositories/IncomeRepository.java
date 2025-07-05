package com.everton.cash_Control.repositories;

import com.everton.cash_Control.entities.Income;
import com.everton.cash_Control.enums.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income,Long> {
    List<Income> findByUser_id(Long userId);
    List<Income> findByPaymentMethod(PaymentMethod paymentMethod);
    List<Income> findByDateBetween(LocalDate startDate, LocalDate endDate);
    List<Income> findIncomeByDate(LocalDate date);
}
