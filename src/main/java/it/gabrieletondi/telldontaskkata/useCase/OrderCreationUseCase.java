package it.gabrieletondi.telldontaskkata.useCase;

import static it.gabrieletondi.telldontaskkata.useCase.OrderBuilder.anOrder;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.Product;
import it.gabrieletondi.telldontaskkata.repository.OrderRepository;
import it.gabrieletondi.telldontaskkata.repository.ProductCatalog;

class OrderCreationUseCase {

  private final OrderRepository orderRepository;
  private final ProductCatalog productCatalog;

  OrderCreationUseCase(OrderRepository orderRepository, ProductCatalog productCatalog) {
    this.orderRepository = orderRepository;
    this.productCatalog = productCatalog;
  }

  void run(SellItemsRequest request) {

//    final List<Product> products = request.getRequests().stream().map(SellItemRequest::getProductName)
//        .map(productCatalog::getByName)
//        .collect(Collectors.toList());

    Order order = anOrder().withoutOrderItems();

    for (SellItemRequest itemRequest : request.getRequests()) {
      final Product product = productCatalog.having(itemRequest.getProductName());
      if (product == null) {
        throw new UnknownProductException();
      }
      order.addOrderItemFor(product, itemRequest.getQuantity());
    }

    orderRepository.save(order);
  }
}
