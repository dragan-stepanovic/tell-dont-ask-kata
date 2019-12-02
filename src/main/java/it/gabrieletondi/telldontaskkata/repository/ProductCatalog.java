package it.gabrieletondi.telldontaskkata.repository;

import it.gabrieletondi.telldontaskkata.domain.Products;
import java.util.List;

public interface ProductCatalog {

  boolean doesNotContainsAllProductsWith(List<String> productNames);

  Products productsWith(List<String> productNames);
}
