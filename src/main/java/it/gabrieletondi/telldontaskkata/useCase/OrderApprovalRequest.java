package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.Order;

abstract class OrderApprovalRequest {

  int orderId;

  int getOrderId() {
    return orderId;
  }

  abstract void updateOrderStatus(Order order);
}
