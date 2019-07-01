package com.github.nesterovilya.budgetmanager.integration.dao;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.nesterovilya.budgetmanager.dao.CategoryRepository;
import com.github.nesterovilya.budgetmanager.dao.PaymentRepository;
import com.github.nesterovilya.budgetmanager.model.Category;
import com.github.nesterovilya.budgetmanager.model.Payment;
import com.github.nesterovilya.budgetmanager.model.enumeration.PaymentType;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Ilya Nesterov
 */
public class PaymentRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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

        Payment foundPayment = paymentRepository.getOne(UUID.fromString("7d8b705a-4b2b-4e18-883d-f5207eac7e5f"));

        assertThat(foundPayment.getId(), is(UUID.fromString("7d8b705a-4b2b-4e18-883d-f5207eac7e5f")));
        assertThat(foundPayment.getCategory(), is(notNullValue(Category.class)));
        assertThat(foundPayment.getCategory().getId(), is(UUID.fromString("03a776ca-bfda-47c9-9e9c-99b0ad34152d")));
        assertThat(foundPayment.getCorrelationId(), is(UUID.fromString("facfbb72-9f7b-4847-abf8-d045f7ec34e8")));
        assertThat(foundPayment.getProductTitle(), is("Milk"));
        /*assertThat(foundPayment.getPaymentDate(), is(ZonedDateTime.parse("2018-05-01T19:44:00.000+03:00")));*/
        assertThat(foundPayment.getPaymentDate().withZoneSameInstant(ZoneOffset.UTC),
                is(ZonedDateTime.parse("2018-05-01T19:44:00.000+03:00").withZoneSameInstant(ZoneOffset.UTC)));
        assertThat(foundPayment.getPaymentType(), is(PaymentType.OUTCOMING));
        assertThat(foundPayment.getAmount(), is(Money.of(CurrencyUnit.EUR, 1.20)));
    }

    @Test
    @Commit
    @DataSet(value = {
            "datasets/category/categories-for-select.yml"
    })
    @ExpectedDataSet(value = {
            "datasets/payment/payment-for-insert.yml"
    })
    public void test_insertPayment() {


        Payment payment = new Payment();
        payment.setCategory(categoryRepository.getOne(UUID.fromString("03a776ca-bfda-47c9-9e9c-99b0ad34152d")));
        payment.setCorrelationId(UUID.fromString("7b681bfe-8c8b-45be-9919-86c28004ede1"));
        payment.setProductTitle("Meet");
        payment.setPaymentDate(ZonedDateTime.parse("2018-05-05T11:25:00.000+03:00"));
        payment.setPaymentType(PaymentType.OUTCOMING);
        payment.setAmount(Money.of(CurrencyUnit.EUR, 5.00));

        paymentRepository.save(payment);
    }
}
