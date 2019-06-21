package com.github.nesterovilya.budgetmanager.dao;

import com.github.nesterovilya.budgetmanager.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Ilya Nesterov
 */

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
