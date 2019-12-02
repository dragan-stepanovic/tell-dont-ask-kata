package it.gabrieletondi.telldontaskkata.domain;

public class Rejected implements OrderStatusNew {

  @Override
  public OrderStatusNew reject() {
    return this;
  }
}
