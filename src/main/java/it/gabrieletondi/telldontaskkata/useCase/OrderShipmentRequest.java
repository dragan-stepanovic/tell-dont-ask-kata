package it.gabrieletondi.telldontaskkata.useCase;

class OrderShipmentRequest {

  private int orderId;

  OrderShipmentRequest(int orderId) {
    this.orderId = orderId;
  }

  int getOrderId() {
    return orderId;
  }
}
