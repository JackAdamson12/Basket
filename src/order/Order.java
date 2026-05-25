package order;

import benefit.Promotion;
import product.Product;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Order
{
    private List<Product> products = new ArrayList<>();

    private List<Promotion> promotions = new ArrayList<>();

    private double finishPrice;

    public void addProduct(Product product)
    {
        if(product == null)
        {
            return;
        }

        products.add(product);

        finishPrice = 0;
    }

    public void removeProduct(Product product)
    {
        products.remove(product);

        finishPrice = 0;
    }

    public void addPromotion(Promotion promotion)
    {
        if(promotion == null)
        {
            return;
        }

        promotions.add(promotion);

        finishPrice = 0;
    }

    public double ScoreTotalPrice()
    {
        double total = 0;

        if(products.isEmpty())
        {
            return 0;
        }

        for(Product p : products)
        {
            total += p.getPrice();
        }

        for(Promotion promotion : promotions)
        {
            total = promotion.apply(products, total);
        }

        finishPrice = total;

        return finishPrice;
    }

    public void saveOrder()
    {
        try
        {
            FileWriter writer = new FileWriter("src/order/Order.txt");

            if(finishPrice == 0)
            {
                finishPrice = ScoreTotalPrice();
            }

            for(Product p : products)
            {
                writer.write(p.getName() + " - " + p.getPrice() + "\n");
            }

            writer.write("Total: " + finishPrice);

            writer.close();
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void showProducts()
    {
        if(products.isEmpty())
        {
            System.out.println(
                    "Basket is empty"
            );

            return;
        }

        for(Product p : products)
        {
            System.out.println(p.getCode() + " " + p.getName() + " " + p.getPrice());
        }
    }

    public void sortProducts(Comparator<Product> comparator)
    {
        products.sort(comparator);
    }

    public List<Product> getProducts()
    {
        return products;
    }

    public double getFinishPrice()
    {
        return finishPrice;
    }
}