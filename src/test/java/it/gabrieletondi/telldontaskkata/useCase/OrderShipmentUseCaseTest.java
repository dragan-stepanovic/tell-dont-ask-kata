package it.gabrieletondi.telldontaskkata.useCase;

import static it.gabrieletondi.telldontaskkata.useCase.OrderBuilder.anOrder;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.OrderStatus;
import it.gabrieletondi.telldontaskkata.doubles.TestOrderRepository;
import it.gabrieletondi.telldontaskkata.doubles.TestShipmentService;
import org.junit.Test;

public class OrderShipmentUseCaseTest {

  private final TestOrderRepository orderRepository = new TestOrderRepository();
  private final TestShipmentService shipmentService = new TestShipmentService();
  private final OrderShipmentUseCase useCase = new OrderShipmentUseCase(orderRepository, shipmentService);

  @Test
  public void shipApprovedOrder() throws Exception {
    final int anOrderId = 2;
    final Order initialOrder = anOrder().with(anOrderId).with(OrderStatus.APPROVED).build();
    orderRepository.add(initialOrder);

    OrderShipmentRequest request = new OrderShipmentRequest(anOrderId);

    useCase.run(request);

    assertTrue(orderRepository.savedOrderHasStatus(OrderStatus.SHIPPED));
    assertThat(shipmentService.getShippedOrder(), is(initialOrder));
  }

  @Test(expected = OrderCannotBeShippedException.class)
  public void createdOrdersCannotBeShipped() throws Exception {
    orderRepository.add(anOrder().with(OrderStatus.CREATED).build());
    OrderShipmentRequest request = new OrderShipmentRequest(1);

    useCase.run(request);

    assertTrue(orderRepository.thereIsNoSavedOrder());
    assertThat(shipmentService.getShippedOrder(), is(nullValue()));
  }

  @Test(expected = OrderCannotBeShippedException.class)
  public void rejectedOrdersCannotBeShipped() throws Exception {
    orderRepository.add(anOrder().with(OrderStatus.REJECTED).build());

    OrderShipmentRequest request = new OrderShipmentRequest(1);

    useCase.run(request);

    assertTrue(orderRepository.thereIsNoSavedOrder());
    assertThat(shipmentService.getShippedOrder(), is(nullValue()));
  }

  @Test(expected = OrderCannotBeShippedTwiceException.class)
  public void shippedOrdersCannotBeShippedAgain() throws Exception {
    orderRepository.add(anOrder().with(OrderStatus.SHIPPED).build());

    OrderShipmentRequest request = new OrderShipmentRequest(1);

    useCase.run(request);

    assertThat(orderRepository.getSavedOrder(), is(nullValue()));
    assertThat(shipmentService.getShippedOrder(), is(nullValue()));
  }
}
