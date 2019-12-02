package it.gabrieletondi.telldontaskkata.useCase.creation;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.repository.OrderRepository;
import it.gabrieletondi.telldontaskkata.repository.ProductCatalog;
import java.util.List;

public class OrderCreationUseCase {

  private final OrderRepository orderRepository;
  private final ProductCatalog productCatalog;

  public OrderCreationUseCase(OrderRepository orderRepository, ProductCatalog productCatalog) {
    this.orderRepository = orderRepository;
    this.productCatalog = productCatalog;
  }

  public void run(SellItemsRequest request) {

    assertWeHaveAllProductsWith(request.productNames());
    Order order = request.orderFromRequest(productCatalog);
    orderRepository.save(order);
  }

  private void assertWeHaveAllProductsWith(List<String> productNames) {
    if (productCatalog.doesNotContainsAllProductsWith(productNames)) {
      throw new UnknownProductException();
    }
  }
}
