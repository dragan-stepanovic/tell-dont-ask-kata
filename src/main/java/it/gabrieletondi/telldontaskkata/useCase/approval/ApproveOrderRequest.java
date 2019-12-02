package it.gabrieletondi.telldontaskkata.useCase.approval;

import it.gabrieletondi.telldontaskkata.domain.Order;

public class ApproveOrderRequest extends OrderApprovalRequest {

  private ApproveOrderRequest(int orderId) {
    this.orderId = orderId;
  }

  public static ApproveOrderRequest forOrderWith(int anOrderId) {
    return new ApproveOrderRequest(anOrderId);
  }

  @Override
  public void updateOrderStatus(Order order) {
    order.approve();
  }
}
