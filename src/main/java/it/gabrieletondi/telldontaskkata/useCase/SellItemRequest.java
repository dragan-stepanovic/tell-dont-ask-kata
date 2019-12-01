package it.gabrieletondi.telldontaskkata.useCase;

public class SellItemRequest {

  private String productName;
  private int quantity;

  SellItemRequest(String productName, int quantity) {
    this.productName = productName;
    this.quantity = quantity;
  }

  public String getProductName() {
    return productName;
  }

  public int getQuantity() {
    return quantity;
  }
}
