package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.Order;

class OrderApprovalRequest {

  int orderId;

  int getOrderId() {
    return orderId;
  }

  void updateOrderStatus(Order order) {
    Order.assertNotChangingShippedOrder(order);
  }

}
