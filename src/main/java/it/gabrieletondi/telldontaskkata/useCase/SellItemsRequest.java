package it.gabrieletondi.telldontaskkata.useCase;

import java.util.List;
import java.util.stream.Collectors;

class SellItemsRequest {

  private List<SellItemRequest> requests;

  SellItemsRequest(List<SellItemRequest> requests) {
    this.requests = requests;
  }

  List<String> productNames() {
    return requests.stream().map(SellItemRequest::getProductName).collect(Collectors.toList());
  }

  List<SellItemRequest> getRequests() {
    return requests;
  }
}
