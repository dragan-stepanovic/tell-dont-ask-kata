package it.gabrieletondi.telldontaskkata.useCase;

import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.SHIPPED;

import it.gabrieletondi.telldontaskkata.domain.Order;

class OrderApprovalRequest {

  int orderId;
  boolean approved;

  private void assertNotChangingShippedOrder(Order order) {
    if (changingShippedOrder(order)) {
      throw new ShippedOrdersCannotBeChangedException();
    }
  }

  int getOrderId() {
    return orderId;
  }

  private boolean changingShippedOrder(Order order) {
    return order.is(SHIPPED);
  }

  void updateOrderStatus(Order order) {
    assertNotChangingShippedOrder(order);
  }
}
