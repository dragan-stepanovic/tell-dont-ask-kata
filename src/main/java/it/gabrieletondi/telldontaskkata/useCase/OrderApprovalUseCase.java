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

    assertNotChangingShippedOrder(order);
    assertNotApprovingRejectedOrder(request, order);
    assertNotRejectingApprovedOrder(request, order);

    order.setStatus(request.isApproved() ? APPROVED : REJECTED);
    orderRepository.save(order);
  }

  private void assertNotRejectingApprovedOrder(OrderApprovalRequest request, Order order) {
    if (request.rejectingApprovedOrder(order)) {
      throw new ApprovedOrderCannotBeRejectedException();
    }
  }

  private void assertNotApprovingRejectedOrder(OrderApprovalRequest request, Order order) {
    if (request.approvingRejectedOrder(order)) {
      throw new RejectedOrderCannotBeApprovedException();
    }
  }

  private void assertNotChangingShippedOrder(Order order) {
    if (changingShippedOrder(order)) {
      throw new ShippedOrdersCannotBeChangedException();
    }
  }

  private boolean changingShippedOrder(Order order) {
    return order.is(SHIPPED);
  }

}
