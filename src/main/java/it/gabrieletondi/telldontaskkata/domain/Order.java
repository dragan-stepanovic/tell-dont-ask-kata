package it.gabrieletondi.telldontaskkata.domain;

import java.math.BigDecimal;
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

  public Order(OrderStatus status, List<OrderItem> items, String currency, BigDecimal total, BigDecimal tax) {
    this.status = status;
    this.items = items;
    this.currency = currency;
    this.total = total;
    this.tax = tax;
  }

  public Order() {
  }

  public Order(int id, OrderStatus status, List<OrderItem> items, String currency, BigDecimal total, BigDecimal tax) {
    this.id = id;
    this.status = status;
    this.items = items;
    this.currency = currency;
    this.total = total;
    this.tax = tax;
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

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
