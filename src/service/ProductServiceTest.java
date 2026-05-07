package service;

import org.junit.jupiter.api.Test;
import product.Product;
import service.ProductServis;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductServiceTest
{
    @Test
    public void getMostExpensiveTest()
    {
        List<Product> products = new ArrayList<>();

        products.add(new Product("01",  "PS5", 2500));
        products.add(new Product("02", "PSGame", 50));

        ProductServis service = new ProductServis();

        Product p = service.getMostExpensive(products);

        assertEquals("PS5", p.getName());
    }
}
