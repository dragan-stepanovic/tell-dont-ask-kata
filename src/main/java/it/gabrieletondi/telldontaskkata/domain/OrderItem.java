package it.gabrieletondi.telldontaskkata.domain;

import java.math.BigDecimal;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class OrderItem {

  private Product product;
  private int quantity;
  private BigDecimal taxedAmount;
  private BigDecimal taxAmount;

  public OrderItem(Product product, int quantity, BigDecimal taxedAmount, BigDecimal taxAmount) {
    this.product = product;
    this.quantity = quantity;
    this.taxedAmount = taxedAmount;
    this.taxAmount = taxAmount;
  }

  static OrderItem forA(Product product, int quantity) {
    return new OrderItem(product, quantity, product.taxedAmountFor(quantity), product.taxAmountFor(quantity));
  }

  BigDecimal addTaxAmountTo(BigDecimal tax) {
    return tax.add(taxAmount);
  }

  BigDecimal addTaxedAmountTo(BigDecimal total) {
    return total.add(taxedAmount);
  }
}
