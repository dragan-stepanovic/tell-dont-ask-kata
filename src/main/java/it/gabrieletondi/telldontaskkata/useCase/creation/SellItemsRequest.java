package it.gabrieletondi.telldontaskkata.useCase.creation;

import it.gabrieletondi.telldontaskkata.domain.Order;
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
      order.add(itemRequest.orderItemFor(products));
    }
    return order;
  }

  List<String> productNames() {
    List<String> productNames = new ArrayList<>();
    requests.forEach(r -> r.addProductNameTo(productNames));
    return productNames;
  }
}
