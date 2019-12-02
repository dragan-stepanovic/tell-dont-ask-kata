package it.gabrieletondi.telldontaskkata.domain;

public interface OrderStatus {

  OrderStatus reject();

  OrderStatus approve();

  OrderStatus ship();
}
