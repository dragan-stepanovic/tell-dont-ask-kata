package it.gabrieletondi.telldontaskkata.useCase;

import java.util.List;
import java.util.stream.Stream;

class SellItemsRequest {

  private List<SellItemRequest> requests;

  SellItemsRequest(List<SellItemRequest> requests) {
    this.requests = requests;
  }

  Stream<String> productNames() {
    return requests.stream().map(SellItemRequest::getProductName);
  }

  List<SellItemRequest> getRequests() {
    return requests;
  }
}
