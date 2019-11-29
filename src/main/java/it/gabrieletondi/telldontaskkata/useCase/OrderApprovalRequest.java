package it.gabrieletondi.telldontaskkata.useCase;

class OrderApprovalRequest {

  private int orderId;
  private boolean approved;

  boolean isNotApproved() {
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
