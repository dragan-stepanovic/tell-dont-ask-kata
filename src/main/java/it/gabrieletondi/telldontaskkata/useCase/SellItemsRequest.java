package it.gabrieletondi.telldontaskkata.useCase;

import java.util.List;

class SellItemsRequest {
    private List<SellItemRequest> requests;

  SellItemsRequest(List<SellItemRequest> requests) {
        this.requests = requests;
    }

  List<SellItemRequest> getRequests() {
        return requests;
    }
}
