package it.gabrieletondi.telldontaskkata.useCase.approval;

import it.gabrieletondi.telldontaskkata.domain.Order;

public abstract class OrderApprovalRequest {

  int orderId;

  int getOrderId() {
    return orderId;
  }

  public abstract void updateOrderStatus(Order order);
}
