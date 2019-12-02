package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.approval.invariants.ShippedOrdersCannotBeApproved;
import it.gabrieletondi.telldontaskkata.useCase.rejection.ShippedOrdersCannotBeRejected;
import it.gabrieletondi.telldontaskkata.useCase.shipment.invariants.OrderCannotBeShippedTwice;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Shipped implements OrderStatus {

  @Override
  public OrderStatus reject() {
    throw new ShippedOrdersCannotBeRejected();
  }

  @Override
  public OrderStatus approve() {
    throw new ShippedOrdersCannotBeApproved();
  }

  @Override
  public OrderStatus ship() {
    throw new OrderCannotBeShippedTwice();
  }
}
