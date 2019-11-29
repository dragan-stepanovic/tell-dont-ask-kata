package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.OrderStatus;
import it.gabrieletondi.telldontaskkata.repository.OrderRepository;

class OrderApprovalUseCase {

  private final OrderRepository orderRepository;

  OrderApprovalUseCase(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  void run(OrderApprovalRequest request) {
    final Order order = orderRepository.getById(request.getOrderId());

    if (order.is(OrderStatus.SHIPPED)) {
      throw new ShippedOrdersCannotBeChangedException();
    }

    if (request.isApproved() && order.is(OrderStatus.REJECTED)) {
      throw new RejectedOrderCannotBeApprovedException();
    }

    if (OrderApprovalRequest.isNotApproved(request) && order.is(OrderStatus.APPROVED)) {
      throw new ApprovedOrderCannotBeRejectedException();
    }

    order.setStatus(request.isApproved() ? OrderStatus.APPROVED : OrderStatus.REJECTED);
    orderRepository.save(order);
  }

}
