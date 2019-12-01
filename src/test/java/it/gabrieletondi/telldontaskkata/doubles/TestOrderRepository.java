package it.gabrieletondi.telldontaskkata.doubles;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.OrderStatus;
import it.gabrieletondi.telldontaskkata.repository.OrderRepository;
import java.util.ArrayList;
import java.util.List;

public class TestOrderRepository implements OrderRepository {

  private Order insertedOrder;
  private List<Order> orders = new ArrayList<>();

  public boolean thereIsNoSavedOrder() {
    return savedOrderIs(null);
  }

  public void save(Order order) {
    this.insertedOrder = order;
  }

  @Override
  public Order getById(int orderId) {
    return orders.stream().filter(o -> o.getId() == orderId).findFirst().get();
  }

  public void add(Order order) {
    this.orders.add(order);
  }

  public boolean savedOrderIs(Order value) {
    return insertedOrder.equals(value);
  }

  public boolean savedOrderHasStatus(OrderStatus status) {
    return insertedOrder.hasStatus(status);
  }
}
