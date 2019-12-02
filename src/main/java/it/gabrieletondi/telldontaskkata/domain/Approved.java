package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.approval.invariants.ApprovedOrderCannotBeRejectedException;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Approved implements OrderStatus {

  @Override
  public OrderStatus reject() {
    throw new ApprovedOrderCannotBeRejectedException();
  }

  @Override
  public OrderStatus approve() {
    return this;
  }

  @Override
  public OrderStatus ship() {
    return new Shipped();
  }
}
