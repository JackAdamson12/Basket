package benefit;


import db.Db;
import product.Product;

import java.util.List;

public class Benefit
{

   private double finishPrice;

   public Benefit(double price)
   {
       finishPrice = price;
   }
    public Benefit(){}

    public double znizka(double price)
    {
        double temp;
        if(price > 300)
        {
            temp = price * 0.05;
            price -= temp;
        }

        return finishPrice = price;
    }
    public double TwoPlusOne(List<Product> p)
    {
        if(p.isEmpty())
        {
            return finishPrice;
        }

        if(p.size() >= 3)
        {
            int i = 0;
            double min = p.get(0).getPrice();
            for(i = 1; i < p.size(); i++)
            {
                if(min > p.get(i).getPrice())
                {
                    min = p.get(i).getPrice();

                }
            }
            p.get(i - 1).setPrice(0);

            return finishPrice -= min;
        }
        return finishPrice;
    }

    public boolean GratisCup(double price)
    {
        if(price > 200)
        {
            return true;
        }
        return false;
    }

    public double coupon(Product product, String c)
    {
        Db db = new Db();

        if(db.CheckCoupon(c))
        {
            double temp = product.getPrice() * 0.3;

            finishPrice -= temp;
        }

        return finishPrice;
    }

    public double GetFinishPrice()
    {
        return finishPrice;
    }


}
