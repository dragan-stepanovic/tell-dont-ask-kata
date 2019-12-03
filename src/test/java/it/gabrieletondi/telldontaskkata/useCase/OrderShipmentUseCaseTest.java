package it.gabrieletondi.telldontaskkata.useCase;

import static it.gabrieletondi.telldontaskkata.useCase.OrderBuilder.anOrder;
import static it.gabrieletondi.telldontaskkata.useCase.OrderBuilder.anOrderId;
import static junit.framework.TestCase.assertTrue;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.doubles.TestOrderRepository;
import it.gabrieletondi.telldontaskkata.doubles.TestShipmentService;
import it.gabrieletondi.telldontaskkata.useCase.shipment.OrderShipmentRequest;
import it.gabrieletondi.telldontaskkata.useCase.shipment.OrderShipmentUseCase;
import it.gabrieletondi.telldontaskkata.useCase.shipment.invariants.OrderCannotBeShippedTwice;
import it.gabrieletondi.telldontaskkata.useCase.shipment.invariants.OrderNotReadyForShippment;
import org.junit.Test;

public class OrderShipmentUseCaseTest {

  private final TestOrderRepository orderRepository = new TestOrderRepository();
  private final TestShipmentService shipmentService = new TestShipmentService();
  private final OrderShipmentUseCase shipment = new OrderShipmentUseCase(orderRepository, shipmentService);

  @Test
  public void shipApprovedOrder() throws Exception {
    final Order initialOrder = anOrder().withId(anOrderId).thatIsApproved().build();
    orderRepository.add(initialOrder);

    shipment.run(OrderShipmentRequest.forOrderWith(anOrderId));

    assertTrue(orderRepository.savedOrderIsShipped());
    assertTrue(shipmentService.shippedOrderMatches(initialOrder));
  }

  @Test(expected = OrderNotReadyForShippment.class)
  public void createdOrdersCannotBeShipped() throws Exception {
    orderRepository.add(anOrder().withId(anOrderId).thatIsCreated().build());

    shipment.run(OrderShipmentRequest.forOrderWith(anOrderId));

    assertTrue(orderRepository.orderIsNotSaved());
    assertTrue(shipmentService.orderIsNotShipped());
  }

  @Test(expected = OrderNotReadyForShippment.class)
  public void rejectedOrdersCannotBeShipped() throws Exception {
    orderRepository.add(anOrder().withId(anOrderId).thatIsRejected().build());

    shipment.run(OrderShipmentRequest.forOrderWith(anOrderId));

    assertTrue(orderRepository.orderIsNotSaved());
    assertTrue(shipmentService.orderIsNotShipped());
  }

  @Test(expected = OrderCannotBeShippedTwice.class)
  public void shippedOrdersCannotBeShippedAgain() throws Exception {
    orderRepository.add(anOrder().withId(anOrderId).thatIsShipped().build());

    shipment.run(OrderShipmentRequest.forOrderWith(anOrderId));

    assertTrue(orderRepository.orderIsNotSaved());
    assertTrue(shipmentService.orderIsNotShipped());
  }

}
