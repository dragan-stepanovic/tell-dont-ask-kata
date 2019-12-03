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

  @Test
  public void sellMultipleItems() throws Exception {
    SellItemRequest saladRequest = new SellItemRequest("salad", 2);
    SellItemRequest tomatoRequest = new SellItemRequest("tomato", 3);
    final SellItemsRequest request = new SellItemsRequest(asList(saladRequest, tomatoRequest));

    orderCreation.run(request);

    final Order expected = anOrder(
        asList(withItem("salad", "3.56", 2, "7.84", "0.72"), withItem("tomato", "4.65", 3, "15.36", "1.41")), 1,
        new Created(), "EUR", new BigDecimal("23.20"), new BigDecimal("2.13"));

    assertTrue(orderRepository.savedOrderMatches(expected));
  }

  private OrderItem withItem(String salad, String price, int quantity, String taxedAmount, String taxAmount) {
    return anOrderItem(salad, price, quantity, taxedAmount, taxAmount);
  }

  private OrderItem anOrderItem(String salad, String price, int quantity, String taxedAmount, String taxAmount) {
    return new OrderItemBuilder(salad, price, quantity, taxedAmount, taxAmount).build();
  }

  private Order anOrder(List<OrderItem> orderItems, int id, Created status, String currency, BigDecimal total,
      BigDecimal tax) {
    return new Order(id, status, orderItems, currency, total, tax);
  }

  @Test(expected = UnknownProductException.class)
  public void unknownProduct() throws Exception {
    SellItemRequest unknownProductRequest = new SellItemRequest("unknown product", 0);
    SellItemsRequest request = new SellItemsRequest(Collections.singletonList(unknownProductRequest));
    orderCreation.run(request);
  }

  private class OrderItemBuilder {

    private String productName;
    private String price;
    private int quantity;
    private String taxedAmount;
    private String taxAmount;

    OrderItemBuilder(String productName, String price, int quantity, String taxedAmount, String taxAmount) {
      this.productName = productName;
      this.price = price;
      this.quantity = quantity;
      this.taxedAmount = taxedAmount;
      this.taxAmount = taxAmount;
    }

    OrderItemBuilder forProductWithName(String productName) {
      this.productName = productName;
      return this;
    }

    OrderItem build() {
      return new OrderItem(new Product(productName, new Price(new BigDecimal(price), foodTaxPercentage)), quantity,
          new BigDecimal(taxedAmount), new BigDecimal(taxAmount));
    }
  }
}
