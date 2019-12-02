package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.creation.UnknownProductException;
import java.util.List;

public class Products {

  private final List<Product> values;

  public Products(List<Product> values) {
    assertThereAreNoUnknownProducts(values);
    this.values = values;
  }

  public Product oneWithThe(String name) {
    return values.stream().filter(p -> p.with(name)).findFirst().orElse(null);
  }

  private void assertThereAreNoUnknownProducts(List<Product> values) {
    if (noValues(values) || atLeastOneUnknownProduct(values)) {
      throw new UnknownProductException();
    }
  }

  private boolean noValues(List<Product> values) {
    return values == null;
  }

  private boolean atLeastOneUnknownProduct(List<Product> values) {
    return values.stream().anyMatch(this::unknown);
  }

  private boolean unknown(Product product) {
    return product == null;
  }
}
