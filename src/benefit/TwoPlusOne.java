package benefit;

import product.Product;

import java.util.List;

public class TwoPlusOne implements Promotion
{
    @Override
    public double apply(List<Product> products, double currentPrice)
    {
        if(products.isEmpty())
        {
            return currentPrice;
        }
        if(products.size() < 3)
        {
            return currentPrice;
        }

            double min = products.get(0).getPrice();

            for(int i = 1; i < products.size(); i++)
            {
                if(min > products.get(i).getPrice())
                {
                    min = products.get(i).getPrice();
                }
            }

            currentPrice -= min;


        return currentPrice;

    }
}
