package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.approval.invariants.ApprovedOrderCannotBeRejectedException;

public class Approved implements OrderStatusNew {

  @Override
  public OrderStatusNew reject() {
    throw new ApprovedOrderCannotBeRejectedException();
  }
}
