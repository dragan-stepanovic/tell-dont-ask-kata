package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.shipment.invariants.OrderNotReadyForShippment;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Created implements OrderStatus {

  @Override
  public OrderStatus reject() {
    return new Rejected();
  }

  @Override
  public OrderStatus approve() {
    return new Approved();
  }

  @Override
  public OrderStatus ship() {
    throw new OrderNotReadyForShippment();
  }
}
