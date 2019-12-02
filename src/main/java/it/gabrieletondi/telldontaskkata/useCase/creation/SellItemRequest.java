package it.gabrieletondi.telldontaskkata.useCase.creation;

import it.gabrieletondi.telldontaskkata.domain.OrderItem;
import it.gabrieletondi.telldontaskkata.domain.Products;
import java.util.List;

public class SellItemRequest {

  private String productName;
  private int quantity;

  public SellItemRequest(String productName, int quantity) {
    this.productName = productName;
    this.quantity = quantity;
  }

  OrderItem orderItemFor(Products products) {
    return OrderItem.forA(products.oneWithThe(productName), quantity);
  }

  void addProductNameTo(List<String> productNames) {
    productNames.add(productName);
  }
}
