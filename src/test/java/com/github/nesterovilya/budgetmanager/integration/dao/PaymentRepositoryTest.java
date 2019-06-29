package com.github.nesterovilya.budgetmanager.integration.dao;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.nesterovilya.budgetmanager.dao.PaymentRepository;
import com.github.nesterovilya.budgetmanager.model.Category;
import com.github.nesterovilya.budgetmanager.model.Payment;
import com.github.nesterovilya.budgetmanager.model.enumeration.PaymentType;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Ilya Nesterov
 */
public class PaymentRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private PaymentRepository paymentRepository;

    @Test
    @DataSet(value = {
            "datasets/payment/payments-for-select.yml",
            "datasets/category/categories-for-select.yml"
    })
    public void test_getPayments() {

        List<Payment> foundPayments = paymentRepository.findAll();

        assertThat(foundPayments, not(emptyCollectionOf(Payment.class)));
        assertThat(foundPayments, hasSize(2));
    }

    @Test
    @DataSet(value = {
            "datasets/payment/payments-for-select.yml",
            "datasets/category/categories-for-select.yml"
    })
    public void test_getPayment() {

        Optional<Payment> foundPaymentOptional = paymentRepository.findById(UUID.fromString("7d8b705a-4b2b-4e18-883d-f5207eac7e5f"));

        assertThat(foundPaymentOptional.isPresent(), is(true));

        Payment foundPayment = foundPaymentOptional.get();
        assertThat(foundPayment.getId(), is(UUID.fromString("7d8b705a-4b2b-4e18-883d-f5207eac7e5f")));
        assertThat(foundPayment.getCategory(), is(notNullValue(Category.class)));
        assertThat(foundPayment.getCategory().getId(), is(UUID.fromString("03a776ca-bfda-47c9-9e9c-99b0ad34152d")));
        assertThat(foundPayment.getCorrelationId(), is(UUID.fromString("facfbb72-9f7b-4847-abf8-d045f7ec34e8")));
        assertThat(foundPayment.getProductTitle(), is("Milk"));
        assertThat(foundPayment.getPaymentDate(), is(ZonedDateTime.parse("2018-05-01T16:44:00.000Z")));
        assertThat(foundPayment.getPaymentType(), is(PaymentType.OUTCOMING));
        assertThat(foundPayment.getAmount(), is(Money.of(CurrencyUnit.EUR, 1.20)));
    }
}
