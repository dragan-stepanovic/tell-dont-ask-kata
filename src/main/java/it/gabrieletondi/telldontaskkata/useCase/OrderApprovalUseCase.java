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

    if (isaBoolean(request, order)) {
      throw new RejectedOrderCannotBeApprovedException();
    }

    if (approvedOrderIsRejected(request, order)) {
      throw new ApprovedOrderCannotBeRejectedException();
    }

    order.setStatus(request.isApproved() ? OrderStatus.APPROVED : OrderStatus.REJECTED);
    orderRepository.save(order);
  }

  private boolean isaBoolean(OrderApprovalRequest request, Order order) {
    return request.isApproved() && order.is(OrderStatus.REJECTED);
  }

  private boolean approvedOrderIsRejected(OrderApprovalRequest request, Order order) {
    return request.isNotApproved() && order.is(OrderStatus.APPROVED);
  }
}
