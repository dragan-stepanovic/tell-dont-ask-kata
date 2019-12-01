package it.gabrieletondi.telldontaskkata.repository;

import it.gabrieletondi.telldontaskkata.domain.Product;
import java.util.List;

public interface ProductCatalog {

  Product productWith(String name);

  boolean doesNotContainsAllProductWith(List<String> productNames);
}
