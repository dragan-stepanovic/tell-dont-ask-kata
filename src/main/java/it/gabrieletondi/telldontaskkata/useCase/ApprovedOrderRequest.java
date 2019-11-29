package it.gabrieletondi.telldontaskkata.useCase;

import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.APPROVED;

import it.gabrieletondi.telldontaskkata.domain.Order;

class ApprovedOrderRequest extends OrderApprovalRequest {

  ApprovedOrderRequest(int orderId) {
    this.approved = true;
    this.orderId = orderId;
  }

  @Override
  void updateOrderStatus(Order order) {
    super.updateOrderStatus(order);
    order.setStatus(APPROVED);
  }
}
