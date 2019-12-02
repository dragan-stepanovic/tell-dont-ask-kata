package it.gabrieletondi.telldontaskkata.doubles;

import it.gabrieletondi.telldontaskkata.domain.Approved;
import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.OrderStatus;
import it.gabrieletondi.telldontaskkata.domain.Rejected;
import it.gabrieletondi.telldontaskkata.domain.Shipped;
import it.gabrieletondi.telldontaskkata.repository.OrderRepository;
import java.util.ArrayList;
import java.util.List;

public class TestOrderRepository implements OrderRepository {

  private Order insertedOrder;
  private List<Order> orders = new ArrayList<>();

  public Order orderWith(int orderId) {
    return orders.stream().filter(o -> o.hasId(orderId)).findFirst().get();
  }

  public void save(Order order) {
    this.insertedOrder = order;
  }

  public void add(Order order) {
    this.orders.add(order);
  }

  public boolean orderIsNotSaved() {
    return savedOrderIs(null);
  }

  public boolean savedOrderMatches(Order value) {
    return insertedOrder.equals(value);
  }

  public boolean savedOrderIsApproved() {
    return savedOrderIs(new Approved());
  }

  public boolean savedOrderIsRejected() {
    return savedOrderIs(new Rejected());
  }

  public boolean savedOrderIsShipped() {
    return savedOrderIs(new Shipped());
  }

  private boolean savedOrderIs(OrderStatus status) {
    return insertedOrder.has(status);
  }
}
