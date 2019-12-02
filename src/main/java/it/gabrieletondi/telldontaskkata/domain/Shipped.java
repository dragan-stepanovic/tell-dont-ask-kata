package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.approval.invariants.ShippedOrdersCannotBeApproved;
import it.gabrieletondi.telldontaskkata.useCase.rejection.invariants.ShippedOrdersCannotBeRejected;
import it.gabrieletondi.telldontaskkata.useCase.shipment.invariants.OrderCannotBeShippedTwice;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Shipped implements OrderStatus {

  @Override
  public OrderStatus toRejected() {
    throw new ShippedOrdersCannotBeRejected();
  }

  @Override
  public OrderStatus toApproved() {
    throw new ShippedOrdersCannotBeApproved();
  }

  @Override
  public OrderStatus toShipped() {
    throw new OrderCannotBeShippedTwice();
  }
}
