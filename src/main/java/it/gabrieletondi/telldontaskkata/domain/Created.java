package it.gabrieletondi.telldontaskkata.domain;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Created implements OrderStatusNew {

  @Override
  public OrderStatusNew reject() {
    return new Rejected();
  }

  @Override
  public OrderStatusNew approve() {
    return new Approved();
  }
}
