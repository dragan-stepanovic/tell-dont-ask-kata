package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.shipment.invariants.OrderNotReadyForShippmentException;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Created implements OrderStatusNew {

  @Override
  public OrderStatusNew reject() {
    return new Rejected();
  }

  @Override
  public OrderStatusNew approve() {
    return new Approved();
  }

  @Override
  public OrderStatusNew ship() {
    throw new OrderNotReadyForShippmentException();
  }
}
