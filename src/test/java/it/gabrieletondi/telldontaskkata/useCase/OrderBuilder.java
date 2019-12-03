package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.Approved;
import it.gabrieletondi.telldontaskkata.domain.Created;
import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.OrderStatus;
import it.gabrieletondi.telldontaskkata.domain.Rejected;
import it.gabrieletondi.telldontaskkata.domain.Shipped;
import java.math.BigDecimal;
import java.util.ArrayList;

class OrderBuilder {

  static int anOrderId = 2;
  private int id = 12;
  private OrderStatus status = new Created();
  private String currency = "EUR";
  private BigDecimal total = BigDecimal.ZERO;
  private BigDecimal tax = BigDecimal.ZERO;

  static OrderBuilder anOrder() {
    return new OrderBuilder();
  }

  OrderBuilder with(int id) {
    this.id = id;
    return this;
  }

  OrderBuilder thatIsCreated() {
    return thatIs(new Created());
  }

  OrderBuilder thatIsRejected() {
    return thatIs(new Rejected());
  }

  OrderBuilder thatIsApproved() {
    return thatIs(new Approved());
  }

  OrderBuilder thatIsShipped() {
    return thatIs(new Shipped());
  }

  private OrderBuilder thatIs(OrderStatus status) {
    this.status = status;
    return this;
  }

  OrderBuilder with(OrderStatus status) {
    this.status = status;
    return this;
  }

  OrderBuilder forCurrency(String currency) {
    this.currency = currency;
    return this;
  }

  Order build() {
    return new Order(id, status, new ArrayList<>(), "EUR", BigDecimal.ZERO, BigDecimal.ZERO);
  }

  OrderBuilder withTotal(BigDecimal total) {
    this.total = total;
    return this;
  }

  OrderBuilder withTax(BigDecimal tax) {
    this.tax = tax;
    return this;
  }
}
