package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.shipment.invariants.OrderNotReadyForShippment;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Created implements OrderStatus {

  @Override
  public OrderStatus toRejected() {
    return new Rejected();
  }

  @Override
  public OrderStatus toApproved() {
    return new Approved();
  }

  @Override
  public OrderStatus toShipped() {
    throw new OrderNotReadyForShippment();
  }
}
