package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.useCase.approval.OrderApprovalRequest;

class RejectOrderRequest extends OrderApprovalRequest {

  RejectOrderRequest(int orderId) {
    this.orderId = orderId;
  }

  @Override
  public void updateOrderStatus(Order order) {
    order.markAsRejected();
  }
}
