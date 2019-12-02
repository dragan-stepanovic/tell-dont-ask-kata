package it.gabrieletondi.telldontaskkata.useCase.rejection;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.useCase.OrderApprovalRequest;

public class RejectOrderRequest extends OrderApprovalRequest {

  private RejectOrderRequest(int orderId) {
    this.orderId = orderId;
  }

  public static RejectOrderRequest forOrderWith(int i) {
    return new RejectOrderRequest(i);
  }

  @Override
  public void updateOrderStatus(Order order) {
    order.reject();
  }
}
