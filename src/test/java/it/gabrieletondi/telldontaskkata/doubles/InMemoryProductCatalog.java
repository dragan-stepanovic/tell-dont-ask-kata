package it.gabrieletondi.telldontaskkata.doubles;

import it.gabrieletondi.telldontaskkata.domain.Product;
import it.gabrieletondi.telldontaskkata.domain.Products;
import it.gabrieletondi.telldontaskkata.repository.ProductCatalog;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryProductCatalog implements ProductCatalog {

  private final Products products;

  public InMemoryProductCatalog(Products products) {
    this.products = products;
  }

  public Product productWith(final String name) {
    return products.oneWithThe(name);
  }

  public boolean doesNotContainsAllProductsWith(List<String> productNames) {
    return productNames
        .stream()
        .map(this::productWith)
        .anyMatch(this::unknown);
  }

  @Override
  public Products productsWith(List<String> productNames) {
    return new Products(productNames.stream().map(this::productWith).collect(Collectors.toList()));
  }

  private boolean unknown(Product product) {
    return product == null;
  }
}
