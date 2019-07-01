package com.github.nesterovilya.budgetmanager.integration.dao;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.nesterovilya.budgetmanager.dao.CategoryRepository;
import com.github.nesterovilya.budgetmanager.model.Category;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;

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
    @DataSet(value = {
            "datasets/category/categories-for-select.yml"
    })
    public void test_getCategories() {
        List<Category> foundCategories = categoryRepository.findAll();

        assertThat(foundCategories, not(emptyCollectionOf(Category.class)));
        assertThat(foundCategories, hasSize(3));
    }

    @Test
    @DataSet(value = {
            "datasets/category/categories-for-select.yml"
    })
    public void test_getCategory() {
        Category foundCategory = categoryRepository.getOne(UUID.fromString("ffc7876c-7b0f-4f0d-8d1f-7642c58fc99a"));

        assertThat(foundCategory.getId(), is(UUID.fromString("ffc7876c-7b0f-4f0d-8d1f-7642c58fc99a")));
        assertThat(foundCategory.getParentCategory(), is(notNullValue(Category.class)));
        assertThat(foundCategory.getTitle(), is("Dairy products"));
        assertThat(foundCategory.getDescription(), is("Category for dairy products"));
    }

    @Test
    @Commit
    @ExpectedDataSet(value = {
            "datasets/category/parent-category-for-insert.yml"
    })
    public void test_insertParentCategory() {

        Category category = new Category();
        category.setParentCategory(null);
        category.setTitle("Furniture");
        category.setDescription("Common category for all types of furniture");

        categoryRepository.save(category);
    }

    /*
    * junit.framework.ComparisonFailure: row count (table=CATEGORY)
    * Expected :1
    * Actual   :4
    * */
    @Test
    @Commit
    @DataSet(value = {
            "datasets/category/categories-for-select.yml"
    })
    @ExpectedDataSet(value = {
            "datasets/category/child-category-for-insert.yml"
    })
    public void test_insertChildCategory() {
        Category category = new Category();
        category.setParentCategory(categoryRepository.getOne(UUID.fromString("03a776ca-bfda-47c9-9e9c-99b0ad34152d")));
        category.setTitle("Flour products");
        category.setDescription("Common category for all types of flour products");

        categoryRepository.save(category);
    }
}
