package it.gabrieletondi.telldontaskkata.useCase;

import static it.gabrieletondi.telldontaskkata.useCase.OrderBuilder.anOrder;
import static junit.framework.TestCase.assertTrue;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.OrderStatus;
import it.gabrieletondi.telldontaskkata.doubles.TestOrderRepository;
import org.junit.Test;

public class OrderApprovalUseCaseTest {

  private final TestOrderRepository orderRepository = new TestOrderRepository();
  private final OrderApprovalUseCase useCase = new OrderApprovalUseCase(orderRepository);

  @Test
  public void approvedExistingOrder() throws Exception {
    Order initialOrder = anOrder().with(OrderStatus.CREATED).build();
    orderRepository.add(initialOrder);

    OrderApprovalRequest request = new ApproveOrderRequest(1);
    useCase.run(request);

    assertTrue(orderRepository.savedOrderHasStatus(OrderStatus.APPROVED));
  }

  @Test
  public void rejectedExistingOrder() throws Exception {
    Order initialOrder = anOrder().with(OrderStatus.CREATED).build();
    orderRepository.add(initialOrder);

    OrderApprovalRequest request = new RejectOrderRequest(1);
    useCase.run(request);

    assertTrue(orderRepository.savedOrderHasStatus(OrderStatus.REJECTED));
  }

  @Test(expected = RejectedOrderCannotBeApprovedException.class)
  public void cannotApproveRejectedOrder() throws Exception {
    Order initialOrder = anOrder().with(OrderStatus.REJECTED).build();
    orderRepository.add(initialOrder);

    OrderApprovalRequest request = new ApproveOrderRequest(1);
    useCase.run(request);

    assertTrue(orderRepository.thereIsNoSavedOrder());
  }

  @Test(expected = ApprovedOrderCannotBeRejectedException.class)
  public void cannotRejectApprovedOrder() throws Exception {
    Order initialOrder = anOrder().with(OrderStatus.APPROVED).build();
    orderRepository.add(initialOrder);

    OrderApprovalRequest request = new RejectOrderRequest(1);
    useCase.run(request);

    assertTrue(orderRepository.thereIsNoSavedOrder());
  }

  @Test(expected = ShippedOrdersCannotBeChangedException.class)
  public void shippedOrdersCannotBeApproved() throws Exception {
    Order initialOrder = anOrder().with(OrderStatus.SHIPPED).build();
    orderRepository.add(initialOrder);

    useCase.run(new ApproveOrderRequest(1));

    assertTrue(orderRepository.thereIsNoSavedOrder());
  }

  @Test(expected = ShippedOrdersCannotBeChangedException.class)
  public void shippedOrdersCannotBeRejected() throws Exception {
    Order initialOrder = anOrder().with(OrderStatus.SHIPPED).build();
    orderRepository.add(initialOrder);

    useCase.run(new RejectOrderRequest(1));

    assertTrue(orderRepository.thereIsNoSavedOrder());
  }
}
