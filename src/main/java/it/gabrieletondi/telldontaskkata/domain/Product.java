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
  private Price price1;

  public Product(String name, BigDecimal price, Category category) {
    this.name = name;
    this.price = price;
    this.price1 = new Price(price, category.getTaxPercentage());
    this.category = category;
  }

  boolean has(String thatName) {
    return this.name.equals(thatName);
  }

  BigDecimal taxAmountFor(int quantity) {
    return price1.unitaryTax().multiply(BigDecimal.valueOf(quantity));
  }

  private BigDecimal unitaryTaxedAmount() {
    return price1.addUnitaryTax().setScale(2, HALF_UP);
  }

  BigDecimal taxedAmountFor(int quantity) {
    return unitaryTaxedAmount().multiply(valueOf(quantity)).setScale(2, HALF_UP);
  }
}
