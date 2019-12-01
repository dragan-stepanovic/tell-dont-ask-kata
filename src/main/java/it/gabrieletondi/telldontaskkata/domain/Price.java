package it.gabrieletondi.telldontaskkata.domain;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Price {

  private final BigDecimal amount;
  private final BigDecimal taxPercentage;

  public Price(BigDecimal amount, BigDecimal taxPercentage) {
    this.amount = amount;
    this.taxPercentage = taxPercentage;
  }

  BigDecimal unitaryTax() {
    return this.amount.divide(valueOf(100)).multiply(this.taxPercentage).setScale(2, HALF_UP);
  }

  BigDecimal includingUnitaryTax() {
    return amount.add(unitaryTax()).setScale(2, HALF_UP);
  }
}
