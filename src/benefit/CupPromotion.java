package benefit;

import product.Product;

import java.util.List;

public class CupPromotion implements Promotion
{
    @Override
    public double apply(List<Product> products,double currentPrice)
    {
        if(currentPrice > 200)
        {
            boolean exists = false;

            for(Product p : products)
            {
                if(p.getCode().equals("GRATIS"))
                {
                    exists = true;
                }
            }

            if(!exists)
            {
                Product cup = new Product("GRATIS", "Cup", 0);

                products.add(cup);
            }
        }

        return currentPrice;
    }
}
