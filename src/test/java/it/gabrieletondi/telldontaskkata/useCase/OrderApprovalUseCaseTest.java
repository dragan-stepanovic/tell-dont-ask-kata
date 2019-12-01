package it.gabrieletondi.telldontaskkata.useCase;

import static it.gabrieletondi.telldontaskkata.useCase.OrderBuilder.anOrder;
import static junit.framework.TestCase.assertTrue;

import it.gabrieletondi.telldontaskkata.domain.OrderStatus;
import it.gabrieletondi.telldontaskkata.doubles.TestOrderRepository;
import it.gabrieletondi.telldontaskkata.useCase.approval.ApproveOrderRequest;
import it.gabrieletondi.telldontaskkata.useCase.approval.OrderApprovalUseCase;
import it.gabrieletondi.telldontaskkata.useCase.approval.RejectOrderRequest;
import it.gabrieletondi.telldontaskkata.useCase.invariants.ApprovedOrderCannotBeRejectedException;
import it.gabrieletondi.telldontaskkata.useCase.invariants.RejectedOrderCannotBeApprovedException;
import it.gabrieletondi.telldontaskkata.useCase.invariants.ShippedOrdersCannotBeChangedException;
import org.junit.Test;

public class OrderApprovalUseCaseTest {

  private final TestOrderRepository orderRepository = new TestOrderRepository();
  private final OrderApprovalUseCase useCase = new OrderApprovalUseCase(orderRepository);

  @Test
  public void approvedExistingOrder() throws Exception {
    orderRepository.add(anOrder().with(OrderStatus.CREATED).build());
    useCase.run(new ApproveOrderRequest(1));
    assertTrue(orderRepository.savedOrderHas(OrderStatus.APPROVED));
  }

  @Test
  public void rejectedExistingOrder() throws Exception {
    orderRepository.add(anOrder().with(OrderStatus.CREATED).build());
    useCase.run(new RejectOrderRequest(1));
    assertTrue(orderRepository.savedOrderHas(OrderStatus.REJECTED));
  }

  @Test(expected = RejectedOrderCannotBeApprovedException.class)
  public void cannotApproveRejectedOrder() throws Exception {
    orderRepository.add(anOrder().with(OrderStatus.REJECTED).build());
    useCase.run(new ApproveOrderRequest(1));
    assertTrue(orderRepository.orderIsNotSaved());
  }

  @Test(expected = ApprovedOrderCannotBeRejectedException.class)
  public void cannotRejectApprovedOrder() throws Exception {
    orderRepository.add(anOrder().with(OrderStatus.APPROVED).build());
    useCase.run(new RejectOrderRequest(1));
    assertTrue(orderRepository.orderIsNotSaved());
  }

  @Test(expected = ShippedOrdersCannotBeChangedException.class)
  public void shippedOrdersCannotBeApproved() throws Exception {
    orderRepository.add(anOrder().with(OrderStatus.SHIPPED).build());
    useCase.run(new ApproveOrderRequest(1));
    assertTrue(orderRepository.orderIsNotSaved());
  }

  @Test(expected = ShippedOrdersCannotBeChangedException.class)
  public void shippedOrdersCannotBeRejected() throws Exception {
    orderRepository.add(anOrder().with(OrderStatus.SHIPPED).build());
    useCase.run(new RejectOrderRequest(1));
    assertTrue(orderRepository.orderIsNotSaved());
  }
}
