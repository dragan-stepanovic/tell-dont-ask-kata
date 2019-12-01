package it.gabrieletondi.telldontaskkata.useCase;

import static it.gabrieletondi.telldontaskkata.useCase.OrderBuilder.anOrder;
import static junit.framework.TestCase.assertTrue;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.OrderStatus;
import it.gabrieletondi.telldontaskkata.doubles.TestOrderRepository;
import it.gabrieletondi.telldontaskkata.doubles.TestShipmentService;
import org.junit.Test;

public class OrderShipmentUseCaseTest {

  private final TestOrderRepository orderRepository = new TestOrderRepository();
  private final TestShipmentService shipmentService = new TestShipmentService();
  private final OrderShipmentUseCase useCase = new OrderShipmentUseCase(orderRepository, shipmentService);
  private int anOrderId = 2;

  @Test
  public void shipApprovedOrder() throws Exception {
    final Order initialOrder = anOrder().with(anOrderId).with(OrderStatus.APPROVED).build();
    orderRepository.add(initialOrder);

    useCase.run(new OrderShipmentRequest(anOrderId));

    assertTrue(orderRepository.savedOrderHasStatus(OrderStatus.SHIPPED));
    assertTrue(shipmentService.shippedOrderIs(initialOrder));
  }

  @Test(expected = OrderNotReadyForShippmentException.class)
  public void createdOrdersCannotBeShipped() throws Exception {
    orderRepository.add(anOrder().with(OrderStatus.CREATED).build());

    useCase.run(new OrderShipmentRequest(1));

    assertTrue(orderRepository.orderIsNotSaved());
    assertTrue(shipmentService.orderIsNotShipped());
  }

  @Test(expected = OrderNotReadyForShippmentException.class)
  public void rejectedOrdersCannotBeShipped() throws Exception {
    orderRepository.add(anOrder().with(OrderStatus.REJECTED).build());

    useCase.run(new OrderShipmentRequest(1));

    assertTrue(orderRepository.orderIsNotSaved());
    assertTrue(shipmentService.orderIsNotShipped());
  }

  @Test(expected = OrderCannotBeShippedTwiceException.class)
  public void shippedOrdersCannotBeShippedAgain() throws Exception {
    orderRepository.add(anOrder().with(OrderStatus.SHIPPED).build());

    useCase.run(new OrderShipmentRequest(1));

    assertTrue(orderRepository.orderIsNotSaved());
    assertTrue(shipmentService.orderIsNotShipped());
  }
}
