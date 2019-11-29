package it.gabrieletondi.telldontaskkata.useCase;

import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.APPROVED;
import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.REJECTED;

import it.gabrieletondi.telldontaskkata.domain.Order;

class ApprovedOrderRequest extends OrderApprovalRequest {

  ApprovedOrderRequest(int orderId) {
    this.approved = true;
    this.orderId = orderId;
  }

  @Override
  void updateOrderStatus(Order order) {
    super.updateOrderStatus(order);
    assertNotApprovingRejectedOrder(order);
    order.setStatus(APPROVED);
  }

  private void assertNotApprovingRejectedOrder(Order order) {
    if (approvingRejectedOrder(order)) {
      throw new RejectedOrderCannotBeApprovedException();
    }
  }

  private boolean approvingRejectedOrder(Order order) {
    return approved && order.is(REJECTED);
  }
}
