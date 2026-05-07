package main;

import benefit.Benefit;
import db.Db;
import order.Order;
import product.Product;
import service.ProductServis;
import sort.ProductNameComparator;
import sort.ProductPriceComparator;

import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        Db db = new Db();

        Order order = new Order();

        Product p1 = db.getByName("PS5");
        Product p2 = db.getByName("PS4");
        Product p3 = db.getByName("PSGame");

        order.addProduct(p1);
        order.addProduct(p2);
        order.addProduct(p3);

        order.sortProducts(new ProductPriceComparator());

        order.showProducts();

        double total = order.ScoreTotalPrice();

        Benefit benefit = new Benefit();

        benefit.znizka(total);
        benefit.TwoPlusOne(order.getProducts());

        total = benefit.GetFinishPrice();

        System.out.println("Final price: " + total);

        if(benefit.GratisCup(total))
        {
            System.out.println("Client gets free cup");
        }

        ProductServis service = new ProductServis();

        Product expensive = service.getMostExpensive(order.getProducts());

        System.out.println(
                "Most expensive: " + expensive.getName()
        );

        List<Product> topProducts =
                service.getNMostExpensive(order.getProducts(), 2);

        for(int i = 0; i < topProducts.size(); i++)
        {
            System.out.println(topProducts.get(i).getName());
        }

        order.saveOrder();
    }
}