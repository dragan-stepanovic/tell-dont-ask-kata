package it.gabrieletondi.telldontaskkata.useCase;

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
      new NotApprovedOrderRequest().updateOrderStatus(order);
    }

    orderRepository.save(order);
  }

}
