package it.gabrieletondi.telldontaskkata.useCase;

import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.APPROVED;
import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.REJECTED;
import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.SHIPPED;

import it.gabrieletondi.telldontaskkata.domain.Order;

class OrderApprovalRequest {

  int orderId;
  boolean approved;

  private void assertNotChangingShippedOrder(Order order) {
    if (changingShippedOrder(order)) {
      throw new ShippedOrdersCannotBeChangedException();
    }
  }

  private void assertNotApprovingRejectedOrder(Order order) {
    if (approvingRejectedOrder(order)) {
      throw new RejectedOrderCannotBeApprovedException();
    }
  }

  private void assertNotRejectingApprovedOrder(Order order) {
    if (rejectingApprovedOrder(order)) {
      throw new ApprovedOrderCannotBeRejectedException();
    }
  }

  int getOrderId() {
    return orderId;
  }

  private boolean changingShippedOrder(Order order) {
    return order.is(SHIPPED);
  }

  private boolean rejectingApprovedOrder(Order order) {
    return notApproved() && order.is(APPROVED);
  }

  private boolean approvingRejectedOrder(Order order) {
    return approved && order.is(REJECTED);
  }

  private boolean notApproved() {
    return !approved;
  }

  void updateOrderStatus(Order order) {
    assertNotChangingShippedOrder(order);
    assertNotApprovingRejectedOrder(order);
    assertNotRejectingApprovedOrder(order);
  }
}
