package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.approval.invariants.ApprovedOrderCannotBeRejectedException;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Approved implements OrderStatusNew {

  @Override
  public OrderStatusNew reject() {
    throw new ApprovedOrderCannotBeRejectedException();
  }

  @Override
  public OrderStatusNew approve() {
    return this;
  }
}
