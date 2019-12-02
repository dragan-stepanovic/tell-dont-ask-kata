package it.gabrieletondi.telldontaskkata.useCase.creation;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.OrderItem;
import it.gabrieletondi.telldontaskkata.domain.Product;
import it.gabrieletondi.telldontaskkata.repository.ProductCatalog;

public class SellItemRequest {

  private String productName;
  private int quantity;

  public SellItemRequest(String productName, int quantity) {
    this.productName = productName;
    this.quantity = quantity;
  }

  void orderItemFrom(ProductCatalog productCatalog, Order order) {
    final Product product = productCatalog.productWith(productName);
    order.add(OrderItem.forA(product, quantity));
  }

  String getProductName() {
    return productName;
  }
}
