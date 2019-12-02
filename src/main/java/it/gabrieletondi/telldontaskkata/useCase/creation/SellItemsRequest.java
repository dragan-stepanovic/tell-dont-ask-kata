package it.gabrieletondi.telldontaskkata.useCase.creation;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.Product;
import it.gabrieletondi.telldontaskkata.domain.Products;
import java.util.List;
import java.util.stream.Collectors;

public class SellItemsRequest {

  private List<SellItemRequest> requests;

  public SellItemsRequest(List<SellItemRequest> requests) {
    this.requests = requests;
  }

  Order orderForRequest(Products products, boolean weDoNotHaveAllProducts) {
    assertWeHaveAllProductsFromRequest(weDoNotHaveAllProducts);
    Order order = Order.withoutOrderItems();

    for (SellItemRequest itemRequest : requests) {
      Product product = products.oneWithThe(itemRequest.getProductName());
      order.add(itemRequest.orderItemFor(product));
    }
    return order;
  }

  private void assertWeHaveAllProductsFromRequest(boolean weDoNotHaveAllProducts) {
    if (weDoNotHaveAllProducts) {
      throw new UnknownProductException();
    }
  }

  List<String> productNames() {
    return requests.stream().map(SellItemRequest::getProductName).collect(Collectors.toList());
  }

}
