package it.gabrieletondi.telldontaskkata.useCase.creation;

import it.gabrieletondi.telldontaskkata.domain.OrderItem;
import it.gabrieletondi.telldontaskkata.domain.Product;

public class SellItemRequest {

  private String productName;
  private int quantity;

  public SellItemRequest(String productName, int quantity) {
    this.productName = productName;
    this.quantity = quantity;
  }

  String getProductName() {
    return productName;
  }

  OrderItem orderItemFor(Product product) {
    return OrderItem.forA(product, quantity);
  }
}
