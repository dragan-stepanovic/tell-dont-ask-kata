package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.Created;
import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.OrderStatus;
import it.gabrieletondi.telldontaskkata.domain.OrderStatusNew;
import java.math.BigDecimal;
import java.util.ArrayList;

class OrderBuilder {

  private int id = 1;
  private OrderStatus status = OrderStatus.CREATED;
  private OrderStatusNew statusNew = new Created();

  static OrderBuilder anOrder() {
    return new OrderBuilder();
  }

  Order build() {
    return new Order(id, status, statusNew, new ArrayList<>(), "EUR", BigDecimal.ZERO, BigDecimal.ZERO);
  }

  OrderBuilder with(OrderStatus status) {
    this.status = status;
    return this;
  }

  OrderBuilder with(int id) {
    this.id = id;
    return this;
  }

  OrderBuilder thatIs(OrderStatusNew status) {
    this.statusNew = status;
    return this;
  }
}
