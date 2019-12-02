package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.approval.invariants.RejectedOrderCannotBeApproved;
import it.gabrieletondi.telldontaskkata.useCase.shipment.invariants.OrderNotReadyForShippment;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Rejected implements OrderStatus {

  @Override
  public OrderStatus toRejected() {
    return this;
  }

  @Override
  public OrderStatus toApproved() {
    throw new RejectedOrderCannotBeApproved();
  }

  @Override
  public OrderStatus toShipped() {
    throw new OrderNotReadyForShippment();
  }
}
