package it.gabrieletondi.telldontaskkata.doubles;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.service.ShipmentService;

public class TestShipmentService implements ShipmentService {

  private Order shippedOrder = null;

  @Override
  public void ship(Order order) {
    order.ship();
    this.shippedOrder = order;
  }

  public boolean shippedOrderMatches(Order order) {
    return shippedOrder.equals(order);
  }

  public boolean orderIsNotShipped() {
    return shippedOrder == null;
  }
}
