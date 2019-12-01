package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.OrderStatus;

class OrderBuilder {

  private int id = 1;
  private OrderStatus status = OrderStatus.CREATED;

  static OrderBuilder anOrder() {
    return new OrderBuilder();
  }

  Order build() {
    final Order order = new Order();
    order.setId(id);
    order.setStatus(status);
    return order;
  }

  OrderBuilder withStatus(OrderStatus status) {
    this.status = status;
    return this;
  }
}
