package it.gabrieletondi.telldontaskkata.domain;

public interface OrderStatus {

  OrderStatus toRejected();

  OrderStatus toApproved();

  OrderStatus toShipped();
}
