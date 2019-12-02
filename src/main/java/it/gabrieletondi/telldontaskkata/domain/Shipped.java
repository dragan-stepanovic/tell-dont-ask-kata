package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.approval.invariants.ShippedOrdersCannotBeApprovedException;
import it.gabrieletondi.telldontaskkata.useCase.approval.invariants.ShippedOrdersCannotBeRejectedException;
import it.gabrieletondi.telldontaskkata.useCase.shipment.invariants.OrderCannotBeShippedTwiceException;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Shipped implements OrderStatus {

  @Override
  public OrderStatus reject() {
    throw new ShippedOrdersCannotBeRejectedException();
  }

  @Override
  public OrderStatus approve() {
    throw new ShippedOrdersCannotBeApprovedException();
  }

  @Override
  public OrderStatus ship() {
    throw new OrderCannotBeShippedTwiceException();
  }
}
