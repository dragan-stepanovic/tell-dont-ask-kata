package it.gabrieletondi.telldontaskkata.useCase;

import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.SHIPPED;

import it.gabrieletondi.telldontaskkata.domain.Order;

class OrderApprovalRequest {

  int orderId;

  int getOrderId() {
    return orderId;
  }

  void updateOrderStatus(Order order) {
    assertNotChangingShippedOrder(order);
  }

  private static void assertNotChangingShippedOrder(Order order) {
    if (changingShippedOrder(order)) {
      throw new ShippedOrdersCannotBeChangedException();
    }
  }

  private static boolean changingShippedOrder(Order order) {
    return order.is(SHIPPED);
  }
}
