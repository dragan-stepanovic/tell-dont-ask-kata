package it.gabrieletondi.telldontaskkata.useCase;

class SellItemRequest {

  private String productName;
  private int quantity;

  SellItemRequest(String productName, int quantity) {
    this.productName = productName;
    this.quantity = quantity;
  }

  String getProductName() {
    return productName;
  }

  int getQuantity() {
    return quantity;
  }

}
