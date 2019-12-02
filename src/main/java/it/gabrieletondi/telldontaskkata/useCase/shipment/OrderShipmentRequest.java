package it.gabrieletondi.telldontaskkata.useCase.shipment;

public class OrderShipmentRequest {

  private int orderId;

  private OrderShipmentRequest(int orderId) {
    this.orderId = orderId;
  }

  public static OrderShipmentRequest forOrderWith(int anOrderId) {
    return new OrderShipmentRequest(anOrderId);
  }

  int getOrderId() {
    return orderId;
  }
}
