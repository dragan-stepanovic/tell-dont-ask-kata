package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.Order;

class OrderBuilder {

  int id = 1;

  Order build() {
    return new Order();
  }
}
