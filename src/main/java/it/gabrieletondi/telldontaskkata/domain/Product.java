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
  private BigDecimal price;
  private Category category;

  public Product(String name, BigDecimal price, Category category) {
    this.name = name;
    this.price = price;
    this.category = category;
  }

  boolean has(String thatName) {
    return this.name.equals(thatName);
  }

  private BigDecimal unitaryTax() {
    return price.divide(valueOf(100)).multiply(category.getTaxPercentage()).setScale(2, HALF_UP);
  }

  BigDecimal taxAmountFor(int quantity) {
    return unitaryTax().multiply(BigDecimal.valueOf(quantity));
  }

  private BigDecimal unitaryTaxedAmount() {
    return price.add(unitaryTax()).setScale(2, HALF_UP);
  }

  BigDecimal taxedAmountFor(int quantity) {
    return unitaryTaxedAmount().multiply(valueOf(quantity)).setScale(2, HALF_UP);
  }
}
