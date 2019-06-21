package com.github.nesterovilya.budgetmanager.integration.dao;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Ilya Nesterov
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("integration-test")
public abstract class AbstractRepositoryTest {
}
