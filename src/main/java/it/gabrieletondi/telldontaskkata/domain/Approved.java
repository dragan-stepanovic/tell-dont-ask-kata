package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.rejection.invariants.ApprovedOrderCannotBeRejected;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Approved implements OrderStatus {

  @Override
  public OrderStatus toRejected() {
    throw new ApprovedOrderCannotBeRejected();
  }

  @Override
  public OrderStatus toApproved() {
    return this;
  }

  @Override
  public OrderStatus toShipped() {
    return new Shipped();
  }
}
