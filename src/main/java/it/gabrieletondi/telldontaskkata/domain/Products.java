package it.gabrieletondi.telldontaskkata.domain;

import java.util.List;

public class Products {

  private final List<Product> values;

  public Products(List<Product> values) {
    this.values = values;
  }

  public Product selectOneHaving(String name) {
    return values.stream().filter(p -> p.has(name)).findFirst().orElse(null);
  }
}
