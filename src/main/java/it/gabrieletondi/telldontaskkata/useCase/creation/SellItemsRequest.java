package it.gabrieletondi.telldontaskkata.useCase.creation;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.OrderItem;
import it.gabrieletondi.telldontaskkata.domain.Product;
import it.gabrieletondi.telldontaskkata.repository.ProductCatalog;
import java.util.List;
import java.util.stream.Collectors;

public class SellItemsRequest {

  private List<SellItemRequest> requests;

  public SellItemsRequest(List<SellItemRequest> requests) {
    this.requests = requests;
  }

  Order orderFromRequest(ProductCatalog productCatalog) {
    assertWeHaveAllProductsFromRequest(productCatalog);
    Order order = Order.withoutOrderItems();
    for (SellItemRequest itemRequest : requests) {
      final Product product = productCatalog.productWith(itemRequest.getProductName());
      final OrderItem orderItem = itemRequest.orderItemFor(product);
      order.add(orderItem);
    }
    return order;
  }

  private void assertWeHaveAllProductsFromRequest(ProductCatalog productCatalog) {
    if (productCatalog.doesNotContainsAllProductsWith(productNames())) {
      throw new UnknownProductException();
    }
  }

  private List<String> productNames() {
    return requests.stream().map(SellItemRequest::getProductName).collect(Collectors.toList());
  }

}
