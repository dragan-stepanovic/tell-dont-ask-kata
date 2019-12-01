package it.gabrieletondi.telldontaskkata.useCase.creation;

import java.util.List;
import java.util.stream.Collectors;

public class SellItemsRequest {

  private List<SellItemRequest> requests;

  public SellItemsRequest(List<SellItemRequest> requests) {
    this.requests = requests;
  }

  List<String> productNames() {
    return requests.stream().map(SellItemRequest::getProductName).collect(Collectors.toList());
  }

  List<SellItemRequest> getRequests() {
    return requests;
  }
}
