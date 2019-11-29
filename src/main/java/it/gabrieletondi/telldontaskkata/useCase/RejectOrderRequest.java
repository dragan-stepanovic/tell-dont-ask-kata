package it.gabrieletondi.telldontaskkata.useCase;

import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.APPROVED;
import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.REJECTED;

import it.gabrieletondi.telldontaskkata.domain.Order;

class RejectOrderRequest extends OrderApprovalRequest {

  RejectOrderRequest(int orderId) {
    this.orderId = orderId;
  }

  @Override
  void updateOrderStatus(Order order) {
    super.updateOrderStatus(order);
    assertNotRejectingApprovedOrder(order);
    order.setStatus(REJECTED);
  }

  private void assertNotRejectingApprovedOrder(Order order) {
    if (rejectingApprovedOrder(order)) {
      throw new ApprovedOrderCannotBeRejectedException();
    }
  }

  private boolean rejectingApprovedOrder(Order order) {
    return order.is(APPROVED);
  }
}
