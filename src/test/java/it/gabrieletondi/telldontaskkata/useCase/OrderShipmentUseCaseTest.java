package it.gabrieletondi.telldontaskkata.useCase;

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

  private static Order orderWithIdAndStatus(int id, OrderStatus created) {
    Order initialOrder = new Order();
    initialOrder.setId(id);
    initialOrder.setStatus(created);
    return initialOrder;
  }

  @Test
  public void shipApprovedOrder() throws Exception {
    orderRepository.add(orderWithIdAndStatus(1, OrderStatus.APPROVED));

    OrderShipmentRequest request = new OrderShipmentRequest();
    request.setOrderId(1);

    useCase.run(request);

    assertThat(orderRepository.getSavedOrder().getStatus(), is(OrderStatus.SHIPPED));
    assertThat(shipmentService.getShippedOrder(), is(orderWithIdAndStatus(1, OrderStatus.APPROVED)));
  }

  @Test(expected = OrderCannotBeShippedException.class)
  public void createdOrdersCannotBeShipped() throws Exception {
    Order initialOrder = orderWithIdAndStatus(1, OrderStatus.CREATED);
    orderRepository.add(initialOrder);

    OrderShipmentRequest request = new OrderShipmentRequest();
    request.setOrderId(1);

    useCase.run(request);

    assertThat(orderRepository.getSavedOrder(), is(nullValue()));
    assertThat(shipmentService.getShippedOrder(), is(nullValue()));
  }

  @Test(expected = OrderCannotBeShippedException.class)
  public void rejectedOrdersCannotBeShipped() throws Exception {
    orderRepository.add(orderWithIdAndStatus(1, OrderStatus.REJECTED));

    OrderShipmentRequest request = new OrderShipmentRequest();
    request.setOrderId(1);

    useCase.run(request);

    assertThat(orderRepository.getSavedOrder(), is(nullValue()));
    assertThat(shipmentService.getShippedOrder(), is(nullValue()));
  }

  @Test(expected = OrderCannotBeShippedTwiceException.class)
  public void shippedOrdersCannotBeShippedAgain() throws Exception {
    orderRepository.add(orderWithIdAndStatus(1, OrderStatus.SHIPPED));

    OrderShipmentRequest request = new OrderShipmentRequest();
    request.setOrderId(1);

    useCase.run(request);

    assertThat(orderRepository.getSavedOrder(), is(nullValue()));
    assertThat(shipmentService.getShippedOrder(), is(nullValue()));
  }
}
