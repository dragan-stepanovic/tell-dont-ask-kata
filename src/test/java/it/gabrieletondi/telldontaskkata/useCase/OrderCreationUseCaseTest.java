package it.gabrieletondi.telldontaskkata.useCase;

import static it.gabrieletondi.telldontaskkata.useCase.OrderBuilder.anOrder;
import static it.gabrieletondi.telldontaskkata.useCase.OrderItemBuilder.anOrderItem;
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
import java.util.List;
import org.junit.Test;

public class OrderCreationUseCaseTest {

  private Category food = new Category("food", new BigDecimal("10"));
  private TestOrderRepository orderRepository = new TestOrderRepository();
  private BigDecimal foodTaxPercentage = food.getTaxPercentage();
  private Product salad = new Product("salad", new Price(new BigDecimal("3.56"), foodTaxPercentage));
  private Product tomato = new Product("tomato", new Price(new BigDecimal("4.65"), foodTaxPercentage));
  private ProductCatalog productCatalog = new InMemoryProductCatalog(containing(salad, tomato));
  private OrderCreationUseCase orderCreation = new OrderCreationUseCase(orderRepository, productCatalog);

  private static Products containing(Product salad, Product tomato) {
    return new Products(asList(salad, tomato));
  }

  private static List<OrderItem> orderItems(OrderItem... items) {
    return asList(items);
  }

  @Test(expected = UnknownProductException.class)
  public void unknownProduct() throws Exception {
    SellItemRequest unknownProductRequest = new SellItemRequest("unknown product", 0);
    SellItemsRequest request = new SellItemsRequest(Collections.singletonList(unknownProductRequest));
    orderCreation.run(request);
  }

  @Test
  public void sellMultipleItems() throws Exception {
    SellItemRequest saladRequest = new SellItemRequest("salad", 2);
    SellItemRequest tomatoRequest = new SellItemRequest("tomato", 3);
    final SellItemsRequest request = new SellItemsRequest(asList(saladRequest, tomatoRequest));

    orderCreation.run(request);

    final Order expected = anOrder().withId(1).thatIs(new Created()).inCurrency("EUR")
        .having(
            orderItems(anOrderItem()
                    .forProductWithName("salad")
                    .withPriceOf("3.56")
                    .withTaxPercentage(foodTaxPercentage)
                    .forQuantity(2)
                    .withTaxedAmount("7.84")
                    .withTaxAmount("0.72")
                    .build(),
                anOrderItem()
                    .forProductWithName("tomato")
                    .withPriceOf("4.65")
                    .withTaxPercentage(foodTaxPercentage)
                    .forQuantity(3)
                    .withTaxedAmount("15.36")
                    .withTaxAmount("1.41")
                    .build()))
        .withTotal("23.20").andTax("2.13")
        .build();

    assertTrue(orderRepository.savedOrderMatches(expected));
  }
}
