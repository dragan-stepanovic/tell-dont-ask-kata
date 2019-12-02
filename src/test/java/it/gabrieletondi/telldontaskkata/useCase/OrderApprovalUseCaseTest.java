package it.gabrieletondi.telldontaskkata.useCase;

import static it.gabrieletondi.telldontaskkata.useCase.OrderBuilder.anOrder;
import static it.gabrieletondi.telldontaskkata.useCase.OrderBuilder.anOrderId;
import static junit.framework.TestCase.assertTrue;

import it.gabrieletondi.telldontaskkata.doubles.TestOrderRepository;
import it.gabrieletondi.telldontaskkata.useCase.approval.ApproveOrderRequest;
import it.gabrieletondi.telldontaskkata.useCase.approval.OrderApprovalUseCase;
import it.gabrieletondi.telldontaskkata.useCase.approval.RejectOrderRequest;
import it.gabrieletondi.telldontaskkata.useCase.approval.invariants.ApprovedOrderCannotBeRejectedException;
import it.gabrieletondi.telldontaskkata.useCase.approval.invariants.RejectedOrderCannotBeApprovedException;
import it.gabrieletondi.telldontaskkata.useCase.approval.invariants.ShippedOrdersCannotBeRejectedException;
import org.junit.Test;

public class OrderApprovalUseCaseTest {

  private final TestOrderRepository orderRepository = new TestOrderRepository();
  private final OrderApprovalUseCase approval = new OrderApprovalUseCase(orderRepository);

  @Test
  public void approvedExistingOrder() throws Exception {
    orderRepository.add(anOrder().with(anOrderId).thatIsCreated().build());
    approval.run(ApproveOrderRequest.forOrderWith(anOrderId));
    assertTrue(orderRepository.savedOrderIsApproved());
  }

  @Test
  public void rejectedExistingOrder() throws Exception {
    orderRepository.add(anOrder().with(anOrderId).thatIsCreated().build());
    approval.run(RejectOrderRequest.forOrderWith(2));
    assertTrue(orderRepository.savedOrderIsRejected());
  }

  @Test(expected = RejectedOrderCannotBeApprovedException.class)
  public void cannotApproveRejectedOrder() throws Exception {
    orderRepository.add(anOrder().with(anOrderId).thatIsRejected().build());
    approval.run(ApproveOrderRequest.forOrderWith(anOrderId));
    assertTrue(orderRepository.orderIsNotSaved());
  }

  @Test(expected = ApprovedOrderCannotBeRejectedException.class)
  public void cannotRejectApprovedOrder() throws Exception {
    orderRepository.add(anOrder().with(anOrderId).thatIsApproved().build());
    approval.run(RejectOrderRequest.forOrderWith(anOrderId));
    assertTrue(orderRepository.orderIsNotSaved());
  }

  @Test(expected = ShippedOrdersCannotBeRejectedException.class)
  public void shippedOrdersCannotBeApproved() throws Exception {
    orderRepository.add(anOrder().with(anOrderId).thatIsShipped().build());
    approval.run(ApproveOrderRequest.forOrderWith(anOrderId));
    assertTrue(orderRepository.orderIsNotSaved());
  }

  @Test(expected = ShippedOrdersCannotBeRejectedException.class)
  public void shippedOrdersCannotBeRejected() throws Exception {
    orderRepository.add(anOrder().with(anOrderId).thatIsShipped().build());
    approval.run(RejectOrderRequest.forOrderWith(anOrderId));
    assertTrue(orderRepository.orderIsNotSaved());
  }
}
