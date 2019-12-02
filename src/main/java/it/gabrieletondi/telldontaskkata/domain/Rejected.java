package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.approval.invariants.RejectedOrderCannotBeApprovedException;
import it.gabrieletondi.telldontaskkata.useCase.shipment.invariants.OrderNotReadyForShippmentException;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Rejected implements OrderStatus {

  @Override
  public OrderStatus reject() {
    return this;
  }

  @Override
  public OrderStatus approve() {
    throw new RejectedOrderCannotBeApprovedException();
  }

  @Override
  public OrderStatus ship() {
    throw new OrderNotReadyForShippmentException();
  }
}
