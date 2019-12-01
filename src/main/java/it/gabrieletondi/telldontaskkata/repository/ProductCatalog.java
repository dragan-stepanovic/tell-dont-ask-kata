package it.gabrieletondi.telldontaskkata.repository;

import it.gabrieletondi.telldontaskkata.domain.Product;

public interface ProductCatalog {

    Product productWith(String name);
}
