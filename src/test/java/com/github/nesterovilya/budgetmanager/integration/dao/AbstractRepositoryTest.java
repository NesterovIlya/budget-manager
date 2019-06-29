package com.github.nesterovilya.budgetmanager.integration.dao;

import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.connection.ConnectionHolder;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Ilya Nesterov
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {DaoTestConfig.class})
@ActiveProfiles("integration-test")
@DBUnit(cacheConnection = false, batchedStatements = true, allowEmptyFields = true)
public abstract class AbstractRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Rule
    public DBUnitRule dbUnitRule = DBUnitRule.instance(
            new ConnectionHolder() {

                private Connection connection;

                @Override
                public Connection getConnection() throws SQLException {
                    if (connection == null || connection.isClosed()) {
                        connection = jdbcTemplate.getDataSource().getConnection();
                    }
                    return connection;
                }
            });
}
