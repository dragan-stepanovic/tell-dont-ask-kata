package it.gabrieletondi.telldontaskkata.doubles;

import it.gabrieletondi.telldontaskkata.domain.Product;
import it.gabrieletondi.telldontaskkata.domain.Products;
import it.gabrieletondi.telldontaskkata.repository.ProductCatalog;
import it.gabrieletondi.telldontaskkata.useCase.SellItemsRequest;

public class InMemoryProductCatalog implements ProductCatalog {

  private final Products products;

  public InMemoryProductCatalog(Products products) {
    this.products = products;
  }

  private static boolean unknown(Product product) {
    return product == null;
  }

  public Product productWith(final String name) {
    return products.oneWithThe(name);
  }

  public boolean notAllProductsFound(SellItemsRequest request) {
    return request.productNames()
        .map(this::productWith)
        .anyMatch(InMemoryProductCatalog::unknown);
  }
}
