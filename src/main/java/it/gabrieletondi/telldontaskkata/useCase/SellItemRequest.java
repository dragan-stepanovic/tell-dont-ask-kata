package it.gabrieletondi.telldontaskkata.useCase;

class SellItemRequest {

  private int quantity;
  private String productName;

  SellItemRequest(String productName, int quantity) {

    this.productName = productName;
    this.quantity = quantity;
  }

  int getQuantity() {
    return quantity;
  }

  String getProductName() {
    return productName;
  }

}
