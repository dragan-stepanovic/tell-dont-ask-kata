package it.gabrieletondi.telldontaskkata.useCase;

import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.REJECTED;

import it.gabrieletondi.telldontaskkata.domain.Order;

public class NotApprovedOrderRequest extends OrderApprovalRequest {

  @Override
  void updateOrderStatus(Order order) {
    order.setStatus(REJECTED);
  }
}
