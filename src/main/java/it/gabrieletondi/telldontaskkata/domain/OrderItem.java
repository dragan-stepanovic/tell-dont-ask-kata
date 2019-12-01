package it.gabrieletondi.telldontaskkata.domain;

import java.math.BigDecimal;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class OrderItem {

  private Product product;
  private int quantity;
  private BigDecimal taxAmount;
  private BigDecimal taxedAmount;

  public OrderItem(Product product, int quantity, BigDecimal taxAmount, BigDecimal taxedAmount) {
    this.product = product;
    this.quantity = quantity;
    this.taxAmount = taxAmount;
    this.taxedAmount = taxedAmount;
  }

  static OrderItem forA(Product product, int quantity) {
    return new OrderItem(product, quantity, product.taxAmountFor(quantity), product.taxedAmountFor(quantity));
  }

  BigDecimal getTaxedAmount() {
    return taxedAmount;
  }

  BigDecimal getTaxAmount() {
    return taxAmount;
  }
}
