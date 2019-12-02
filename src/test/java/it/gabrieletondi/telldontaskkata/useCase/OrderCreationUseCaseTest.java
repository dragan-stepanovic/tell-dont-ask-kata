package it.gabrieletondi.telldontaskkata.useCase;

import static java.util.Arrays.asList;
import static junit.framework.TestCase.assertTrue;

import it.gabrieletondi.telldontaskkata.domain.Category;
import it.gabrieletondi.telldontaskkata.domain.Created;
import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.OrderItem;
import it.gabrieletondi.telldontaskkata.domain.Price;
import it.gabrieletondi.telldontaskkata.domain.Product;
import it.gabrieletondi.telldontaskkata.domain.Products;
import it.gabrieletondi.telldontaskkata.doubles.InMemoryProductCatalog;
import it.gabrieletondi.telldontaskkata.doubles.TestOrderRepository;
import it.gabrieletondi.telldontaskkata.repository.ProductCatalog;
import it.gabrieletondi.telldontaskkata.useCase.creation.OrderCreationUseCase;
import it.gabrieletondi.telldontaskkata.useCase.creation.SellItemRequest;
import it.gabrieletondi.telldontaskkata.useCase.creation.SellItemsRequest;
import it.gabrieletondi.telldontaskkata.useCase.creation.UnknownProductException;
import java.math.BigDecimal;
import java.util.Collections;
import org.junit.Test;

public class OrderCreationUseCaseTest {

  private final TestOrderRepository orderRepository = new TestOrderRepository();
  private final Category food = new Category("food", new BigDecimal("10"));
  private BigDecimal foodTaxPercentage = food.getTaxPercentage();
  final Product salad = new Product("salad", new Price(new BigDecimal("3.56"), foodTaxPercentage));
  final Product tomato = new Product("tomato", new Price(new BigDecimal("4.65"), foodTaxPercentage));
  private final ProductCatalog productCatalog = productCatalogContaining(salad, tomato);
  ;

  private static InMemoryProductCatalog productCatalogContaining(Product salad, Product tomato) {
    return new InMemoryProductCatalog(
        new Products(asList(
            salad,
            tomato)));
  }

  private final OrderCreationUseCase orderCreation = new OrderCreationUseCase(orderRepository, productCatalog);

  @Test
  public void sellMultipleItems() throws Exception {
    SellItemRequest saladRequest = new SellItemRequest("salad", 2);
    SellItemRequest tomatoRequest = new SellItemRequest("tomato", 3);
    final SellItemsRequest request = new SellItemsRequest(asList(saladRequest, tomatoRequest));

    orderCreation.run(request);

    final Order expected = new Order(1, new Created(),
        asList(new OrderItem(new Product("salad", new Price(new BigDecimal("3.56"), foodTaxPercentage)), 2,
                new BigDecimal("7.84"), new BigDecimal("0.72")),
            new OrderItem(new Product("tomato", new Price(new BigDecimal("4.65"), foodTaxPercentage)), 3,
                new BigDecimal("15.36"), new BigDecimal("1.41"))),
        "EUR", new BigDecimal("23.20"), new BigDecimal("2.13"));

    assertTrue(orderRepository.savedOrderMatches(expected));
  }

  @Test(expected = UnknownProductException.class)
  public void unknownProduct() throws Exception {
    SellItemRequest unknownProductRequest = new SellItemRequest("unknown product", 0);
    SellItemsRequest request = new SellItemsRequest(Collections.singletonList(unknownProductRequest));
    orderCreation.run(request);
  }
}
