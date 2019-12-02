package it.gabrieletondi.telldontaskkata.useCase.approval;

import it.gabrieletondi.telldontaskkata.domain.Order;

public class ApproveOrderRequest extends OrderApprovalRequest {

  public ApproveOrderRequest(int orderId) {
    this.orderId = orderId;
  }

  @Override
  public void updateOrderStatus(Order order) {
    order.approve();
  }
}
