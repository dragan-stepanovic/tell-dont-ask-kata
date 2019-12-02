package it.gabrieletondi.telldontaskkata.domain;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Rejected implements OrderStatusNew {

  @Override
  public OrderStatusNew reject() {
    return this;
  }
}
