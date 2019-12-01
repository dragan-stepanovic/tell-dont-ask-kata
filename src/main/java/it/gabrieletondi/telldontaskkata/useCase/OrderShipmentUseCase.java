package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.Order;
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

    order.assertNotShippedAlready();
    order.assertCanBeShipped();
    shipmentService.ship(order);

    order.markAsShipped();
    orderRepository.save(order);
  }

}
