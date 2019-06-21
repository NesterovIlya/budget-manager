package com.github.nesterovilya.budgetmanager.system;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author Ilya Nesterov
 */

@SpringBootTest
@ActiveProfiles("system-test")
public class DummySystemTest {

    @Test
    public void testContextBootstrap() {}

}
