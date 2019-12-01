package it.gabrieletondi.telldontaskkata.doubles;

import it.gabrieletondi.telldontaskkata.domain.Product;
import it.gabrieletondi.telldontaskkata.domain.Products;
import it.gabrieletondi.telldontaskkata.repository.ProductCatalog;
import java.util.List;

public class InMemoryProductCatalog implements ProductCatalog {

  private final Products products;

  public InMemoryProductCatalog(Products products) {
    this.products = products;
  }

  public Product productWith(final String name) {
    return products.oneWithThe(name);
  }

  public boolean doesNotContainsAllProductWith(List<String> productNames) {
    return productNames
        .stream()
        .map(this::productWith)
        .anyMatch(this::unknown);
  }

  private boolean unknown(Product product) {
    return product == null;
  }
}
