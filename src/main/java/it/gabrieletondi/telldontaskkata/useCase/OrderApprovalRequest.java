package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.Order;

public abstract class OrderApprovalRequest {

  protected int orderId;

  public int getOrderId() {
    return orderId;
  }

  public abstract void updateOrderStatus(Order order);
}
