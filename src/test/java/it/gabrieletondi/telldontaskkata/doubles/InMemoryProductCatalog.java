package it.gabrieletondi.telldontaskkata.doubles;

import it.gabrieletondi.telldontaskkata.domain.Product;
import it.gabrieletondi.telldontaskkata.domain.Products;
import it.gabrieletondi.telldontaskkata.repository.ProductCatalog;
import java.util.List;

public class InMemoryProductCatalog implements ProductCatalog {

    private final Products products;

    public InMemoryProductCatalog(List<Product> products) {
        this.products = new Products(products);
    }

    public Product having(final String name) {
        return products.having(name);
    }
}
