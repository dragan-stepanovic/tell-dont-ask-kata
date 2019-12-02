package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.approval.invariants.ShippedOrdersCannotBeRejectedException;
import it.gabrieletondi.telldontaskkata.useCase.shipment.invariants.OrderCannotBeShippedTwiceException;
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

  @Override
  public OrderStatusNew ship() {
    throw new OrderCannotBeShippedTwiceException();
  }
}
