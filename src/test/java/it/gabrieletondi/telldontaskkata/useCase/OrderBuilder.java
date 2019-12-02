package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.Created;
import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.OrderStatus;
import it.gabrieletondi.telldontaskkata.domain.Rejected;
import java.math.BigDecimal;
import java.util.ArrayList;

class OrderBuilder {

  private int id = 1;
  private OrderStatus statusNew = new Created();

  static OrderBuilder anOrder() {
    return new OrderBuilder();
  }

  Order build() {
    return new Order(id, statusNew, new ArrayList<>(), "EUR", BigDecimal.ZERO, BigDecimal.ZERO);
  }

  OrderBuilder with(int id) {
    this.id = id;
    return this;
  }

  OrderBuilder thatIs(OrderStatus status) {
    this.statusNew = status;
    return this;
  }

  OrderBuilder thatIsCreated() {
    return thatIs(new Created());
  }

  OrderBuilder thatIsRejected() {
    return thatIs(new Rejected());
  }
}
