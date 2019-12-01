package it.gabrieletondi.telldontaskkata.domain;

import java.util.List;

public class Products {

  private final List<Product> values;

  public Products(List<Product> values) {
    this.values = values;
  }

  public Product oneWithThe(String name) {
    return values.stream().filter(p -> p.with(name)).findFirst().orElse(null);
  }
}
