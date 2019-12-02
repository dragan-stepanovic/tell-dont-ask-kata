package it.gabrieletondi.telldontaskkata.doubles;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.OrderStatus;
import it.gabrieletondi.telldontaskkata.repository.OrderRepository;
import java.util.ArrayList;
import java.util.List;

public class TestOrderRepository implements OrderRepository {

  private Order insertedOrder;
  private List<Order> orders = new ArrayList<>();

  @Override
  public Order orderWith(int orderId) {
    return orders.stream().filter(o -> o.hasId(orderId)).findFirst().get();
  }

  public boolean orderIsNotSaved() {
    return savedOrderIs((OrderStatus) null);
  }

  public void save(Order order) {
    this.insertedOrder = order;
  }

  public void add(Order order) {
    this.orders.add(order);
  }

  public boolean savedOrderIs(Order value) {
    return insertedOrder.equals(value);
  }

  public boolean savedOrderIs(OrderStatus status) {
    return insertedOrder.has(status);
  }
}
