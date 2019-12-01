package it.gabrieletondi.telldontaskkata.domain;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Product {

  private String name;
  private Price price;

  public Product(String name, BigDecimal amount, BigDecimal taxPercentage) {
    this.name = name;
    this.price = new Price(amount, taxPercentage);
  }

  boolean has(String thatName) {
    return this.name.equals(thatName);
  }

  BigDecimal taxAmountFor(int quantity) {
    return price.unitaryTax().multiply(BigDecimal.valueOf(quantity));
  }

  private BigDecimal unitaryTaxedAmount() {
    return price.addUnitaryTax();
  }

  BigDecimal taxedAmountFor(int quantity) {
    return unitaryTaxedAmount().multiply(valueOf(quantity)).setScale(2, HALF_UP);
  }
}
