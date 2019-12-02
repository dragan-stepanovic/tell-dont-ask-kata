package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.creation.UnknownProductException;
import java.util.List;

public class Products {

  private final List<Product> values;

  public Products(List<Product> values) {
    assertThereAreNoUnknownProducts(values);
    this.values = values;
  }

  private static void assertThereAreNoUnknownProducts(List<Product> values) {
    if (noValues(values) || atLeastOneUnknownProduct(values)) {
      throw new UnknownProductException();
    }
  }

  private static boolean atLeastOneUnknownProduct(List<Product> values) {
    return values.stream().anyMatch(Products::unknown);
  }

  private static boolean noValues(List<Product> values) {
    return values == null;
  }

  private static boolean unknown(Product product) {
    return product == null;
  }

  public Product oneWithThe(String name) {
    return values.stream().filter(p -> p.with(name)).findFirst().orElse(null);
  }
}
