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

  public static Order withoutOrderItems() {
    return new Order(OrderStatus.CREATED, new ArrayList<>(), "EUR", new BigDecimal("0.00"),
        new BigDecimal("0.00"));
  }

  public void addOrderItemFor(Product product, int quantity) {
    add(OrderItem.forA(product, quantity));
  }

  private void add(OrderItem orderItem) {
    items.add(orderItem);
    total = total.add(orderItem.getTaxedAmount());
    tax = tax.add(orderItem.getTaxAmount());
  }

  public String getCurrency() {
    return currency;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public List<OrderItem> getItems() {
    return items;
  }

  public BigDecimal getTax() {
    return tax;
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
