package it.gabrieletondi.telldontaskkata.useCase.creation;

import it.gabrieletondi.telldontaskkata.domain.Order;
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
    Order order = Order.withoutOrderItems();
    for (SellItemRequest itemRequest : requests) {
      final Product product = productCatalog.productWith(itemRequest.getProductName());
      order.addOrderItemFor(product, itemRequest.getQuantity());
    }
    return order;
  }

  List<String> productNames() {
    return requests.stream().map(SellItemRequest::getProductName).collect(Collectors.toList());
  }
}
