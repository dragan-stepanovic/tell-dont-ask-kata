package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.Order;

class ApproveOrderRequest extends OrderApprovalRequest {

  ApproveOrderRequest(int orderId) {
    this.orderId = orderId;
  }

  @Override
  void updateOrderStatus(Order order) {
    order.markAsApproved();
  }
}
