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

  Order orderFor(Products products) {
    Order order = Order.withoutOrderItems();
    for (SellItemRequest request : requests) {
      Product product = products.oneWithThe(request.getProductName());
      int quantity = request.getQuantity();
      order.add(product, quantity);
    }
    return order;
  }

  List<String> productNames() {
    return requests.stream().map(SellItemRequest::getProductName).collect(Collectors.toList());
  }
}
