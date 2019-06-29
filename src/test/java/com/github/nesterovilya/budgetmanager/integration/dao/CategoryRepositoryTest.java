package com.github.nesterovilya.budgetmanager.integration.dao;

import com.github.nesterovilya.budgetmanager.dao.CategoryRepository;
import com.github.nesterovilya.budgetmanager.model.Category;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Ilya Nesterov
 */

public class CategoryRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void test_getCategories() {
        List<Category> foundCategories = categoryRepository.findAll();

        assertThat(foundCategories, not(emptyCollectionOf(Category.class)));
        assertThat(foundCategories, hasSize(2));
    }
}
