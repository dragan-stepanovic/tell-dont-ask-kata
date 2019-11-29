package it.gabrieletondi.telldontaskkata.useCase;

import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.REJECTED;

import it.gabrieletondi.telldontaskkata.domain.Order;

class NotApprovedOrderRequest extends OrderApprovalRequest {

  NotApprovedOrderRequest() {
    approved = false;
  }

  @Override
  void updateOrderStatus(Order order) {
    order.setStatus(REJECTED);
  }
}
