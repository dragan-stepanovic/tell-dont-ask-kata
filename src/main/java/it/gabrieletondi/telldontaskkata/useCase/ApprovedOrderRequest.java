package it.gabrieletondi.telldontaskkata.useCase;

import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.APPROVED;

import it.gabrieletondi.telldontaskkata.domain.Order;

class ApprovedOrderRequest extends OrderApprovalRequest {

  @Override
  void updateOrderStatus(Order order) {
    order.setStatus(APPROVED);
  }
}
