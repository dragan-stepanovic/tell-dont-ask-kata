package it.gabrieletondi.telldontaskkata.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Order {

  private int id;
  private OrderStatusNew newStatus;
  private List<OrderItem> items;
  private String currency;
  private BigDecimal total;
  private BigDecimal tax;

  public Order(int id, OrderStatusNew newStatus,
      List<OrderItem> items, String currency, BigDecimal total, BigDecimal tax) {
    this.id = id;
    this.newStatus = newStatus;
    this.items = items;
    this.currency = currency;
    this.total = total;
    this.tax = tax;
  }

  public static Order withoutOrderItems() {
    return new Order(1, new Created(), new ArrayList<>(), "EUR", new BigDecimal("0.00"),
        new BigDecimal("0.00"));
  }

  public boolean hasId(int orderId) {
    return id == orderId;
  }

  public void reject() {
    this.newStatus = newStatus.reject();
  }

  public void approve() {
    this.newStatus = newStatus.approve();
  }

  public void ship() {
    this.newStatus = newStatus.ship();
  }

  public void addOrderItemFor(Product product, int quantity) {
    add(OrderItem.forA(product, quantity));
  }

  private void add(OrderItem orderItem) {
    items.add(orderItem);
    total = orderItem.addTaxedAmountTo(total);
    tax = orderItem.addTaxAmountTo(tax);
  }

  public boolean has(OrderStatusNew status) {
    return newStatus.equals(status);
  }
}
