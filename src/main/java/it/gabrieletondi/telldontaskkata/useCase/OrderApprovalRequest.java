package it.gabrieletondi.telldontaskkata.useCase;

import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.APPROVED;
import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.REJECTED;
import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.SHIPPED;

import it.gabrieletondi.telldontaskkata.domain.Order;

class OrderApprovalRequest {

  private int orderId;
  private boolean approved;

  void assertNotChangingShippedOrder(Order order) {
    if (changingShippedOrder(order)) {
      throw new ShippedOrdersCannotBeChangedException();
    }
  }

  private boolean changingShippedOrder(Order order) {
    return order.is(SHIPPED);
  }

  void assertNotApprovingRejectedOrder(Order order) {
    if (approvingRejectedOrder(order)) {
      throw new RejectedOrderCannotBeApprovedException();
    }
  }

  void assertNotRejectingApprovedOrder(Order order) {
    if (rejectingApprovedOrder(order)) {
      throw new ApprovedOrderCannotBeRejectedException();
    }
  }

  private boolean rejectingApprovedOrder(Order order) {
    return notApproved() && order.is(APPROVED);
  }

  boolean approvingRejectedOrder(Order order) {
    return approved && order.is(REJECTED);
  }

  private boolean notApproved() {
    return !approved;
  }

  int getOrderId() {
    return orderId;
  }

  void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  boolean isApproved() {
    return approved;
  }

  void setApproved(boolean approved) {
    this.approved = approved;
  }
}
