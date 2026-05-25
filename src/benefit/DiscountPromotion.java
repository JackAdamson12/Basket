package benefit;

import product.Product;

import java.util.List;

public class DiscountPromotion implements Promotion
{
    private double discount;

    public void setDiscount(double discount)
    {
        if(discount < 0 || discount > 1)
        {
            return;
        }

        this.discount = discount;
    }

    @Override
    public double apply(List<Product> products,double currentPrice)
    {
        if(currentPrice > 300)
        {
            return currentPrice - (currentPrice * discount);
        }

        return currentPrice;
    }
}

