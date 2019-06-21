package com.github.nesterovilya.budgetmanager.model;

import com.github.nesterovilya.budgetmanager.model.enumeration.PaymentType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmountAndCurrency;
import org.joda.money.Money;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * @author Ilya Nesterov
 */

@Entity
@Getter @Setter
@TypeDef(name = "moneyAmountWithCurrencyType", typeClass = PersistentMoneyAmountAndCurrency.class)
public class Payment extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private String correlationId;

    private String productTitle;

    private ZonedDateTime paymentDate;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Columns(columns = { @Column(name = "currency"), @Column(name = "amount") })
    @Type(type = "moneyAmountWithCurrencyType")
    private Money amount;

}
