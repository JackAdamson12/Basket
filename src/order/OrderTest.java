package order;


import order.Order;
import org.junit.jupiter.api.Test;
import product.Product;
import static org.junit.jupiter.api.Assertions.assertEquals;


    public class OrderTest
    {
        @Test
        public void totalPriceTest()
        {
            Order order = new Order();

            order.addProduct(new Product("01", "PS5", 2500));
            order.addProduct(new Product("02", "Xbox", 2200));

            assertEquals(4465, order.ScoreTotalPrice());
        }
    }

