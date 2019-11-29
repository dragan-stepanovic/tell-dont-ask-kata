package it.gabrieletondi.telldontaskkata.useCase;

import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.REJECTED;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.repository.OrderRepository;

class OrderApprovalUseCase {

  private final OrderRepository orderRepository;

  OrderApprovalUseCase(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  void run(OrderApprovalRequest request) {
    final Order order = orderRepository.getById(request.getOrderId());

    request.assertNotChangingShippedOrder(order);
    request.assertNotApprovingRejectedOrder(order);
    request.assertNotRejectingApprovedOrder(order);


    if (request.isApproved()) {
      new ApprovedOrderRequest().updateOrderStatus(order);
    } else {
      order.setStatus(REJECTED);
    }

    orderRepository.save(order);
  }

}
