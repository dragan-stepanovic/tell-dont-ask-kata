package it.gabrieletondi.telldontaskkata.repository;

import it.gabrieletondi.telldontaskkata.domain.Product;
import it.gabrieletondi.telldontaskkata.useCase.SellItemsRequest;

public interface ProductCatalog {

  Product productWith(String name);

  boolean notAllProductsFound(SellItemsRequest request);
}
