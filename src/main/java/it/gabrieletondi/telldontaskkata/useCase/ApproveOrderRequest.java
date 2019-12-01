package it.gabrieletondi.telldontaskkata.useCase;

import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.REJECTED;

import it.gabrieletondi.telldontaskkata.domain.Order;

class ApproveOrderRequest extends OrderApprovalRequest {

  ApproveOrderRequest(int orderId) {
    this.orderId = orderId;
  }

  @Override
  void updateOrderStatus(Order order) {
    super.updateOrderStatus(order);
    assertNotApprovingRejectedOrder(order);
    order.markAsApproved();
  }

  private static void assertNotApprovingRejectedOrder(Order order) {
    if (approvingRejectedOrder(order)) {
      throw new RejectedOrderCannotBeApprovedException();
    }
  }

  private static boolean approvingRejectedOrder(Order order) {
    return order.is(REJECTED);
  }
}
