package it.gabrieletondi.telldontaskkata.repository;

import it.gabrieletondi.telldontaskkata.domain.Product;
import it.gabrieletondi.telldontaskkata.domain.Products;
import java.util.List;

public interface ProductCatalog {

  Product productWith(String name);

  boolean doesNotContainsAllProductsWith(List<String> productNames);

  Products productsWith(List<String> productNames);
}
