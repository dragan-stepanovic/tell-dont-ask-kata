package it.gabrieletondi.telldontaskkata.domain;

import java.math.BigDecimal;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class OrderItem {

  private Product product;
  private int quantity;
  private BigDecimal price;
  private BigDecimal taxAmount;

  public OrderItem(Product product, int quantity, BigDecimal price, BigDecimal taxAmount) {
    this.product = product;
    this.quantity = quantity;
    this.price = price;
    this.taxAmount = taxAmount;
  }

  static OrderItem forA(Product product, int quantity) {
    return new OrderItem(product, quantity, product.taxedAmountFor(quantity), product.taxAmountFor(quantity));
  }

  BigDecimal getPrice() {
    return price;
  }

  BigDecimal getTaxAmount() {
    return taxAmount;
  }
}
