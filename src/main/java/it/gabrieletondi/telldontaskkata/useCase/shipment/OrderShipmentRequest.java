package it.gabrieletondi.telldontaskkata.useCase.shipment;

public class OrderShipmentRequest {

  private int orderId;

  public OrderShipmentRequest(int orderId) {
    this.orderId = orderId;
  }

  int getOrderId() {
    return orderId;
  }
}
