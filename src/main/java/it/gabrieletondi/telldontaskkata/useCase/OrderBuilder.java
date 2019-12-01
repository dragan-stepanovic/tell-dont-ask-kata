package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.OrderStatus;
import java.math.BigDecimal;
import java.util.ArrayList;

class OrderBuilder {

  private int id = 1;
  private OrderStatus status = OrderStatus.CREATED;

  static OrderBuilder anOrder() {
    return new OrderBuilder();
  }

  static Order withoutOrderItems() {
    return new Order(1, OrderStatus.CREATED, new ArrayList<>(), "EUR", new BigDecimal("0.00"),
        new BigDecimal("0.00"));
  }

  Order build() {
    return new Order(id, status, new ArrayList<>(), "EUR", BigDecimal.ZERO, BigDecimal.ZERO);
  }

  OrderBuilder with(OrderStatus status) {
    this.status = status;
    return this;
  }

  OrderBuilder with(int id) {
    this.id = id;
    return this;
  }
}
