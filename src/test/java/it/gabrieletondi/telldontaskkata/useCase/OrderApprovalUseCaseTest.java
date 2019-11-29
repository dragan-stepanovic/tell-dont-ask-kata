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
    Order initialOrder = new Order();
    initialOrder.setStatus(OrderStatus.CREATED);
    initialOrder.setId(1);
    orderRepository.add(initialOrder);

    OrderApprovalRequest request1 = new ApprovedOrderRequest();
    request1.setOrderId(1);

    useCase.run(request1);

    final Order savedOrder = orderRepository.getSavedOrder();
    assertThat(savedOrder.getStatus(), is(OrderStatus.APPROVED));
  }

  @Test
  public void rejectedExistingOrder() throws Exception {
    Order initialOrder = new Order();
    initialOrder.setStatus(OrderStatus.CREATED);
    initialOrder.setId(1);
    orderRepository.add(initialOrder);

    OrderApprovalRequest request1 = new NotApprovedOrderRequest();
    request1.setOrderId(1);

    useCase.run(request1);

    final Order savedOrder = orderRepository.getSavedOrder();
    assertThat(savedOrder.getStatus(), is(OrderStatus.REJECTED));
  }

  @Test(expected = RejectedOrderCannotBeApprovedException.class)
  public void cannotApproveRejectedOrder() throws Exception {
    Order initialOrder = new Order();
    initialOrder.setStatus(OrderStatus.REJECTED);
    initialOrder.setId(1);
    orderRepository.add(initialOrder);

    OrderApprovalRequest request1 = new ApprovedOrderRequest();
    request1.setOrderId(1);

    useCase.run(request1);

    assertThat(orderRepository.getSavedOrder(), is(nullValue()));
  }

  @Test(expected = ApprovedOrderCannotBeRejectedException.class)
  public void cannotRejectApprovedOrder() throws Exception {
    Order initialOrder = new Order();
    initialOrder.setStatus(OrderStatus.APPROVED);
    initialOrder.setId(1);
    orderRepository.add(initialOrder);

    OrderApprovalRequest request1 = new NotApprovedOrderRequest();
    request1.setOrderId(1);

    useCase.run(request1);

    assertThat(orderRepository.getSavedOrder(), is(nullValue()));
  }

  @Test(expected = ShippedOrdersCannotBeChangedException.class)
  public void shippedOrdersCannotBeApproved() throws Exception {
    Order initialOrder = new Order();
    initialOrder.setStatus(OrderStatus.SHIPPED);
    initialOrder.setId(1);
    orderRepository.add(initialOrder);

    OrderApprovalRequest request1 = new ApprovedOrderRequest();
    request1.setOrderId(1);

    useCase.run(request1);

    assertThat(orderRepository.getSavedOrder(), is(nullValue()));
  }

  @Test(expected = ShippedOrdersCannotBeChangedException.class)
  public void shippedOrdersCannotBeRejected() throws Exception {
    Order initialOrder = new Order();
    initialOrder.setStatus(OrderStatus.SHIPPED);
    initialOrder.setId(1);
    orderRepository.add(initialOrder);

    OrderApprovalRequest request1 = new NotApprovedOrderRequest();
    request1.setOrderId(1);

    useCase.run(request1);

    assertThat(orderRepository.getSavedOrder(), is(nullValue()));
  }
}
