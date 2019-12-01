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

  public Product(String name, Price price) {
    this.name = name;
    this.price = price;
  }

  private static BigDecimal multiply(BigDecimal value, int quantity) {
    return value.multiply(valueOf(quantity)).setScale(2, HALF_UP);
  }

  boolean with(String thatName) {
    return name.equals(thatName);
  }

  BigDecimal taxedAmountFor(int quantity) {
    return multiply(price.includingUnitaryTax(), quantity);
  }

  BigDecimal taxAmountFor(int quantity) {
    return multiply(price.unitaryTaxAmount(), quantity);
  }
}
