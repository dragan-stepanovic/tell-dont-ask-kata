package it.gabrieletondi.telldontaskkata.useCase.creation;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.Products;
import java.util.List;
import java.util.stream.Collectors;

public class SellItemsRequest {

  private List<SellItemRequest> requests;

  public SellItemsRequest(List<SellItemRequest> requests) {
    this.requests = requests;
  }

  Order orderFor(Products products) {
    Order order = Order.withoutOrderItems();
    for (SellItemRequest request : requests) {
      order.add(request.orderItemFor(products.oneWithThe(request.getProductName())));
    }
    return order;
  }

  List<String> productNames() {
    return requests.stream().map(SellItemRequest::getProductName).collect(Collectors.toList());
  }
}
