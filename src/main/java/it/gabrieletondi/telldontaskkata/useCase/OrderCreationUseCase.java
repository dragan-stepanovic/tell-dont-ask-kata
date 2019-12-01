package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.Product;
import it.gabrieletondi.telldontaskkata.repository.OrderRepository;
import it.gabrieletondi.telldontaskkata.repository.ProductCatalog;
import java.util.List;

class OrderCreationUseCase {

  private final OrderRepository orderRepository;
  private final ProductCatalog productCatalog;

  OrderCreationUseCase(OrderRepository orderRepository, ProductCatalog productCatalog) {
    this.orderRepository = orderRepository;
    this.productCatalog = productCatalog;
  }

  void run(SellItemsRequest request) {

    assertContainsAllProductsWith(request.productNames());

    Order order = Order.withoutOrderItems();
    for (SellItemRequest itemRequest : request.getRequests()) {
      final Product product = productCatalog.productWith(itemRequest.getProductName());
      order.addOrderItemFor(product, itemRequest.getQuantity());
    }

    orderRepository.save(order);
  }

  private void assertContainsAllProductsWith(List<String> productNames) {
    if (productCatalog.doesNotContainsAllProductsWith(productNames)) {
      throw new UnknownProductException();
    }
  }
}
