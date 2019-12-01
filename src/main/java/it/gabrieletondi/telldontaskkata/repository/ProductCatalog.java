package it.gabrieletondi.telldontaskkata.repository;

import it.gabrieletondi.telldontaskkata.domain.Product;
import it.gabrieletondi.telldontaskkata.useCase.SellItemsRequest;

public interface ProductCatalog {

  static boolean notAllProductsFound(SellItemsRequest request, ProductCatalog productCatalog) {
    return request.productNames()
        .map(productCatalog::productWith)
        .anyMatch(ProductCatalog::unknown);
  }

  static boolean unknown(Product product) {
    return product == null;
  }

  Product productWith(String name);
}
