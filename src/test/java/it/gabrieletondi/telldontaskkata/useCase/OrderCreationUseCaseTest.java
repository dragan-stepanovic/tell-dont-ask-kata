package it.gabrieletondi.telldontaskkata.useCase;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import it.gabrieletondi.telldontaskkata.domain.Category;
import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.OrderItem;
import it.gabrieletondi.telldontaskkata.domain.OrderStatus;
import it.gabrieletondi.telldontaskkata.domain.Product;
import it.gabrieletondi.telldontaskkata.domain.Products;
import it.gabrieletondi.telldontaskkata.doubles.InMemoryProductCatalog;
import it.gabrieletondi.telldontaskkata.doubles.TestOrderRepository;
import it.gabrieletondi.telldontaskkata.repository.ProductCatalog;
import java.math.BigDecimal;
import java.util.Collections;
import org.junit.Test;

public class OrderCreationUseCaseTest {

  private final TestOrderRepository orderRepository = new TestOrderRepository();
  private final Category food = new Category("food", new BigDecimal("10"));
  private final ProductCatalog productCatalog = new InMemoryProductCatalog(
      new Products(asList(
          new Product("salad", new BigDecimal("3.56"), food),
          new Product("tomato", new BigDecimal("4.65"), food))));
  private final OrderCreationUseCase useCase = new OrderCreationUseCase(orderRepository, productCatalog);

  @Test
  public void sellMultipleItems() throws Exception {
    SellItemRequest saladRequest = new SellItemRequest("salad", 2);
    SellItemRequest tomatoRequest = new SellItemRequest("tomato", 3);
    final SellItemsRequest request = new SellItemsRequest(asList(saladRequest, tomatoRequest));

    useCase.run(request);

    final Order insertedOrder = orderRepository.getSavedOrder();
    final Order expected = new Order(OrderStatus.CREATED,
        asList(new OrderItem(new Product("salad", new BigDecimal("3.56"), food), 2, new BigDecimal("0.72"),
                new BigDecimal("7.84")),
            new OrderItem(new Product("tomato", new BigDecimal("4.65"), food), 3, new BigDecimal("1.41"),
                new BigDecimal("15.36"))),
        "EUR", new BigDecimal("23.20"), new BigDecimal("2.13"));

    assertEquals(expected, insertedOrder);
  }

  @Test(expected = UnknownProductException.class)
  public void unknownProduct() throws Exception {
    SellItemRequest unknownProductRequest = new SellItemRequest("unknown product", 0);
    SellItemsRequest request = new SellItemsRequest(Collections.singletonList(unknownProductRequest));
    useCase.run(request);
  }
}
