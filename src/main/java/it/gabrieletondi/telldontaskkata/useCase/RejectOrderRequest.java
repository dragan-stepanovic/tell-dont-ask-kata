package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.Order;

class RejectOrderRequest extends OrderApprovalRequest {

  RejectOrderRequest(int orderId) {
    this.orderId = orderId;
  }

  @Override
  void updateOrderStatus(Order order) {
    order.markAsRejected();
  }
}
