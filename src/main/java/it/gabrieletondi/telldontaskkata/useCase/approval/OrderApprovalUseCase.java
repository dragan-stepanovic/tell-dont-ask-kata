package it.gabrieletondi.telldontaskkata.useCase.approval;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.repository.OrderRepository;

public class OrderApprovalUseCase {

  private final OrderRepository orderRepository;

  public OrderApprovalUseCase(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public void run(OrderApprovalRequest request) {
    final Order order = orderRepository.getById(request.getOrderId());
    request.updateOrderStatus(order);
    orderRepository.save(order);
  }
}
