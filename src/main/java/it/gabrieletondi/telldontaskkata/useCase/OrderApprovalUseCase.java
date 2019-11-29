package it.gabrieletondi.telldontaskkata.useCase;

import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.APPROVED;
import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.REJECTED;
import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.SHIPPED;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.repository.OrderRepository;

class OrderApprovalUseCase {

  private final OrderRepository orderRepository;

  OrderApprovalUseCase(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  void run(OrderApprovalRequest request) {
    final Order order = orderRepository.getById(request.getOrderId());

    if (order.is(SHIPPED)) {
      throw new ShippedOrdersCannotBeChangedException();
    }

    if (isaBoolean(request, order)) {
      throw new RejectedOrderCannotBeApprovedException();
    }

    if (approvedOrderIsRejected(request, order)) {
      throw new ApprovedOrderCannotBeRejectedException();
    }

    order.setStatus(request.isApproved() ? APPROVED : REJECTED);
    orderRepository.save(order);
  }

  private boolean isaBoolean(OrderApprovalRequest request, Order order) {
    return request.isApproved() && order.is(REJECTED);
  }

  private boolean approvedOrderIsRejected(OrderApprovalRequest request, Order order) {
    return request.isNotApproved() && order.is(APPROVED);
  }
}
