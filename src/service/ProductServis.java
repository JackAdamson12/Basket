package service;

import product.Product;
import sort.ProductPriceComparator;

import java.util.ArrayList;
import java.util.List;

public class ProductServis
{
    public Product getMostExpensive(List<Product> products)
    {
        if(products.isEmpty())
        {
            return null;
        }

        Product max = products.get(0);

        for(int i = 1; i < products.size(); i++)
        {
            if(products.get(i).getPrice() > max.getPrice())
            {
                max = products.get(i);
            }
        }

        return max;
    }

    public Product getCheapest(List<Product> products)
    {
        if(products.isEmpty())
        {
            return null;
        }

        Product min = products.get(0);

        for(int i = 1; i < products.size(); i++)
        {
            if(products.get(i).getPrice() < min.getPrice())
            {
                min = products.get(i);
            }
        }

        return min;
    }

    public List<Product> getNMostExpensive(List<Product> products, int n)
    {
        List<Product> tempProducts = new ArrayList<>(products);

        tempProducts.sort(new ProductPriceComparator());

        List<Product> result = new ArrayList<>();

        for(int i = 0; i < n && i < tempProducts.size(); i++)
        {
            result.add(tempProducts.get(i));
        }

        return result;
    }
}