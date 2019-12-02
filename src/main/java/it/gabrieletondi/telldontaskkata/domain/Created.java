package it.gabrieletondi.telldontaskkata.domain;

public class Created implements OrderStatusNew {

  @Override
  public OrderStatusNew reject() {
    return new Rejected();
  }
}
