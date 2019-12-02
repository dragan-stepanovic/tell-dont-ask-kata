package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.approval.invariants.ShippedOrdersCannotBeRejectedException;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Shipped implements OrderStatusNew {

  @Override
  public OrderStatusNew reject() {
    throw new ShippedOrdersCannotBeRejectedException();
  }

  @Override
  public OrderStatusNew approve() {
    throw new ShippedOrdersCannotBeRejectedException();
  }
}
