package com.github.nesterovilya.budgetmanager.dao;

import com.github.nesterovilya.budgetmanager.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Ilya Nesterov
 */

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
