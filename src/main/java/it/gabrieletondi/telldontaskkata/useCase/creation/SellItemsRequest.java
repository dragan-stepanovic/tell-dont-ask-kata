package it.gabrieletondi.telldontaskkata.useCase.creation;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.Product;
import it.gabrieletondi.telldontaskkata.domain.Products;
import java.util.ArrayList;
import java.util.List;

public class SellItemsRequest {

  private List<SellItemRequest> requests;

  public SellItemsRequest(List<SellItemRequest> requests) {
    this.requests = requests;
  }

  Order orderFor(Products products) {
    Order order = Order.withoutOrderItems();
    for (SellItemRequest itemRequest : requests) {
      final Product product = products.oneWithThe(itemRequest.getProductName());
      order.add(itemRequest.orderItemFor(product));
    }
    return order;
  }

  List<String> productNames() {
    List<String> productNames = new ArrayList<>();
    requests.forEach(r -> r.addProductNameTo(productNames));
    return productNames;
  }
}
