package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.OrderItem;
import it.gabrieletondi.telldontaskkata.domain.Price;
import it.gabrieletondi.telldontaskkata.domain.Product;
import java.math.BigDecimal;

class OrderItemBuilder {

  private String productName = "";
  private String price;
  private int quantity;
  private String taxedAmount;
  private String taxAmount;
  private BigDecimal taxPercentage;

  static OrderItemBuilder anOrderItem() {
    return new OrderItemBuilder();
  }

  OrderItemBuilder forProductWithName(String productName) {
    this.productName = productName;
    return this;
  }

  OrderItemBuilder withPrice(String price) {
    this.price = price;
    return this;
  }

  OrderItemBuilder forQuantity(int quantity) {
    this.quantity = quantity;
    return this;
  }

  OrderItemBuilder withTaxedAmount(String taxedAmount) {
    this.taxedAmount = taxedAmount;
    return this;
  }

  OrderItemBuilder withTaxAmount(String taxAmount) {
    this.taxAmount = taxAmount;
    return this;
  }

  OrderItemBuilder withTaxPercentage(BigDecimal taxPercentage) {
    this.taxPercentage = taxPercentage;
    return this;
  }

  OrderItem build() {
    return new OrderItem(new Product(productName, new Price(new BigDecimal(price), taxPercentage)), quantity,
        new BigDecimal(taxedAmount), new BigDecimal(taxAmount));
  }
}
