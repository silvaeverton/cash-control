package com.everton.cash_Control.repositories;

import com.everton.cash_Control.entities.CashRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashRegisterRepository extends JpaRepository<CashRegister,Long> {
}
