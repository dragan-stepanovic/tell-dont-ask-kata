package it.gabrieletondi.telldontaskkata.useCase;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.OrderStatus;
import it.gabrieletondi.telldontaskkata.doubles.TestOrderRepository;
import org.junit.Test;

public class OrderApprovalUseCaseTest {

  private final TestOrderRepository orderRepository = new TestOrderRepository();
  private final OrderApprovalUseCase useCase = new OrderApprovalUseCase(orderRepository);

  @Test
  public void approvedExistingOrder() throws Exception {
    Order initialOrder = orderWithStatus(OrderStatus.CREATED);
    orderRepository.add(initialOrder);

    OrderApprovalRequest request = new ApproveOrderRequest(1);

    useCase.run(request);
    final Order savedOrder = orderRepository.getSavedOrder();
    assertThat(savedOrder.getStatus(), is(OrderStatus.APPROVED));
  }

  @Test
  public void rejectedExistingOrder() throws Exception {
    Order initialOrder = orderWithStatus(OrderStatus.CREATED);
    orderRepository.add(initialOrder);

    OrderApprovalRequest request = new RejectOrderRequest(1);

    useCase.run(request);

    final Order savedOrder = orderRepository.getSavedOrder();
    assertThat(savedOrder.getStatus(), is(OrderStatus.REJECTED));
  }

  @Test(expected = RejectedOrderCannotBeApprovedException.class)
  public void cannotApproveRejectedOrder() throws Exception {
    Order initialOrder = orderWithStatus(OrderStatus.REJECTED);
    orderRepository.add(initialOrder);

    OrderApprovalRequest request = new ApproveOrderRequest(1);

    useCase.run(request);

    assertThat(orderRepository.getSavedOrder(), is(nullValue()));
  }

  @Test(expected = ApprovedOrderCannotBeRejectedException.class)
  public void cannotRejectApprovedOrder() throws Exception {
    Order initialOrder = orderWithStatus(OrderStatus.APPROVED);
    orderRepository.add(initialOrder);

    OrderApprovalRequest request = new RejectOrderRequest(1);

    useCase.run(request);

    assertThat(orderRepository.getSavedOrder(), is(nullValue()));
  }

  @Test(expected = ShippedOrdersCannotBeChangedException.class)
  public void shippedOrdersCannotBeApproved() throws Exception {
    Order initialOrder = orderWithStatus(OrderStatus.SHIPPED);
    orderRepository.add(initialOrder);

    OrderApprovalRequest request = new ApproveOrderRequest(1);

    useCase.run(request);

    assertThat(orderRepository.getSavedOrder(), is(nullValue()));
  }

  @Test(expected = ShippedOrdersCannotBeChangedException.class)
  public void shippedOrdersCannotBeRejected() throws Exception {
    Order initialOrder = orderWithStatus(OrderStatus.SHIPPED);
    orderRepository.add(initialOrder);

    OrderApprovalRequest request = new RejectOrderRequest(1);

    useCase.run(request);

    assertThat(orderRepository.getSavedOrder(), is(nullValue()));
  }

  private Order orderWithStatus(OrderStatus shipped) {
    Order initialOrder = new Order();
    initialOrder.setStatus(shipped);
    initialOrder.setId(1);
    return initialOrder;
  }
}
