package it.gabrieletondi.telldontaskkata.repository;

import it.gabrieletondi.telldontaskkata.domain.Product;

public interface ProductCatalog {

    Product having(String name);
}
