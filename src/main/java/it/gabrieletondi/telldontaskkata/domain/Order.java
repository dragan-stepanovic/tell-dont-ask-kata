package it.gabrieletondi.telldontaskkata.domain;

import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.APPROVED;
import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.CREATED;
import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.REJECTED;
import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.SHIPPED;

import it.gabrieletondi.telldontaskkata.useCase.OrderCannotBeShippedTwiceException;
import it.gabrieletondi.telldontaskkata.useCase.OrderNotReadyForShippmentException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Order {

  private int id;
  private OrderStatus status;
  private List<OrderItem> items;
  private String currency;
  private BigDecimal total;
  private BigDecimal tax;

  public Order(int id, OrderStatus status, List<OrderItem> items, String currency, BigDecimal total, BigDecimal tax) {
    this.id = id;
    this.status = status;
    this.items = items;
    this.currency = currency;
    this.total = total;
    this.tax = tax;
  }

  public static Order withoutOrderItems() {
    return new Order(1, OrderStatus.CREATED, new ArrayList<>(), "EUR", new BigDecimal("0.00"),
        new BigDecimal("0.00"));
  }

  private void assertNotShippedAlready() {
    if (shippedAlready()) {
      throw new OrderCannotBeShippedTwiceException();
    }
  }

  public void assertCanBeShipped() {
    assertNotShippedAlready();
    assertReadyForShipment();
  }

  private void assertReadyForShipment() {
    if (notReadyForShipment()) {
      throw new OrderNotReadyForShippmentException();
    }
  }

  private boolean notReadyForShipment() {
    return is(CREATED) || is(REJECTED);
  }

  private boolean shippedAlready() {
    return is(SHIPPED);
  }

  public void markAsRejected() {
    this.status = REJECTED;
  }

  public void markAsApproved() {
    this.status = APPROVED;
  }

  public void markAsShipped() {
    this.status = OrderStatus.SHIPPED;
  }

  public boolean is(OrderStatus thatStatus) {
    return this.status == thatStatus;
  }

  public void addOrderItemFor(Product product, int quantity) {
    add(OrderItem.forA(product, quantity));
  }

  private void add(OrderItem orderItem) {
    items.add(orderItem);
    total = total.add(orderItem.getTaxedAmount());
    tax = tax.add(orderItem.getTaxAmount());
  }

  public int getId() {
    return id;
  }

  public boolean hasStatus(OrderStatus thatStatus) {
    return this.status == thatStatus;
  }
}
