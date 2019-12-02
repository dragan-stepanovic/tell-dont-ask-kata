package it.gabrieletondi.telldontaskkata.domain;

public interface OrderStatusNew {

  OrderStatusNew reject();

  OrderStatusNew approve();

  OrderStatusNew ship();
}
