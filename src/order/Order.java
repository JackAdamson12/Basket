package order;

import benefit.Benefit;
import product.Product;
import sort.ProductNameComparator;
import sort.ProductPriceComparator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Order
{
    private List<Product> products = new ArrayList<>();
    private double finishPrice;

    public void addProduct(Product product)
    {
        if(product == null)
        {
            return;
        }
        products.add(product);
    }

    public double inputCoupon(Product product, String s)
    {
        Benefit b = new Benefit(finishPrice);

        finishPrice = b.coupon(product, s);

        return finishPrice;
    }

    public double ScoreTotalPrice()
    {
        double total = 0;

        for(int i = 0; i < products.size(); i++)
        {
            Product p = products.get(i);
            total += p.getPrice();
        }
        Benefit b = new Benefit(total);
        finishPrice = b.znizka(total);
        finishPrice = b.TwoPlusOne(products);

        return finishPrice;
    }


    public void saveOrder()
    {
        try
        {
            FileWriter writer = new FileWriter("src/order/Order.txt");

            finishPrice = ScoreTotalPrice();
            for(int i = 0; i < products.size();i++)
            {
                Product p = products.get(i);
                writer.write(p.getName()+ " - "+ p.getPrice()+ "\n");
            }

            Benefit b = new Benefit();
            if(b.GratisCup(finishPrice))
            {
                Product p = new Product("0","Cup", 0);
                addProduct(p);
                writer.write(p.getName()+" - "+"Gratis" +"\n");
            }

            writer.write("Total: " + finishPrice);

            writer.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    public void removeProduct(Product product)
    {
        products.remove(product);
    }

    public void showProducts()
    {
        for(int i = 0; i < products.size(); i++)
        {
            Product p = products.get(i);

            System.out.println(
                    p.getCode() + " " +
                            p.getName() + " " +
                            p.getPrice()
            );
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


}