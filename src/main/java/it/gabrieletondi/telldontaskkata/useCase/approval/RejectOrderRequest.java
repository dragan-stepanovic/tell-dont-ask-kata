package it.gabrieletondi.telldontaskkata.useCase.approval;

import it.gabrieletondi.telldontaskkata.domain.Order;

public class RejectOrderRequest extends OrderApprovalRequest {

  public RejectOrderRequest(int orderId) {
    this.orderId = orderId;
  }

  @Override
  public void updateOrderStatus(Order order) {
    order.markAsRejected();
  }
}
