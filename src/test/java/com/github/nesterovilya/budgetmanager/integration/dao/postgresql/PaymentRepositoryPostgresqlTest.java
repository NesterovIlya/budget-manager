package com.github.nesterovilya.budgetmanager.integration.dao.postgresql;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.nesterovilya.budgetmanager.integration.dao.DaoTestConfig;
import com.github.nesterovilya.budgetmanager.integration.dao.PaymentRepositoryTest;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Ilya Nesterov
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {DaoTestConfig.class})
@ActiveProfiles(value = "postgresql-integration-test", inheritProfiles = false)
@DBUnit(cacheConnection = false, batchedStatements = true, allowEmptyFields = true)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PaymentRepositoryPostgresqlTest extends PaymentRepositoryTest {
}
