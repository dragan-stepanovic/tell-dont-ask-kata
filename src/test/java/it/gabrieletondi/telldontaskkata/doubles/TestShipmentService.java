package it.gabrieletondi.telldontaskkata.doubles;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.service.ShipmentService;

public class TestShipmentService implements ShipmentService {

  private Order shippedOrder = null;

  public Order getShippedOrder() {
    return shippedOrder;
  }

  @Override
  public void ship(Order order) {
    this.shippedOrder = order;
  }

  public boolean shippedOrderIs(Order order) {
    return shippedOrder.equals(order);
  }

  public boolean orderIsNotShipped() {
    return shippedOrder == null;
  }
}
