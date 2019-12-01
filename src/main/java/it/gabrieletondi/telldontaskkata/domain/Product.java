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

  boolean has(String thatName) {
    return this.name.equals(thatName);
  }

  BigDecimal taxAmountFor(int quantity) {
    return price.unitaryTaxAmount().multiply(valueOf(quantity));
  }

  BigDecimal taxedAmountFor(int quantity) {
    return price.includingUnitaryTax().multiply(valueOf(quantity)).setScale(2, HALF_UP);
  }
}
