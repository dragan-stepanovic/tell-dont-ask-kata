package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.approval.invariants.RejectedOrderCannotBeApprovedException;
import it.gabrieletondi.telldontaskkata.useCase.shipment.invariants.OrderNotReadyForShippmentException;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Rejected implements OrderStatusNew {

  @Override
  public OrderStatusNew reject() {
    return this;
  }

  @Override
  public OrderStatusNew approve() {
    throw new RejectedOrderCannotBeApprovedException();
  }

  @Override
  public OrderStatusNew ship() {
    throw new OrderNotReadyForShippmentException();
  }
}
