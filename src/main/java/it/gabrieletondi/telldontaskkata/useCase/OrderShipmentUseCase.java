package it.gabrieletondi.telldontaskkata.useCase;

import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.CREATED;
import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.REJECTED;
import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.SHIPPED;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.OrderStatus;
import it.gabrieletondi.telldontaskkata.repository.OrderRepository;
import it.gabrieletondi.telldontaskkata.service.ShipmentService;

class OrderShipmentUseCase {

  private final OrderRepository orderRepository;
  private final ShipmentService shipmentService;

  OrderShipmentUseCase(OrderRepository orderRepository, ShipmentService shipmentService) {
    this.orderRepository = orderRepository;
    this.shipmentService = shipmentService;
  }

  void run(OrderShipmentRequest request) {
    final Order order = orderRepository.getById(request.getOrderId());

    if (order.is(CREATED) || order.is(REJECTED)) {
      throw new OrderCannotBeShippedException();
    }

    if (order.is(SHIPPED)) {
      throw new OrderCannotBeShippedTwiceException();
    }

    shipmentService.ship(order);

    order.setStatus(OrderStatus.SHIPPED);
    orderRepository.save(order);
  }

}
