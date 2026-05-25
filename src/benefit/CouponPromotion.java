package benefit;

import db.Db;
import product.Product;

import java.util.List;

public class CouponPromotion implements Promotion
{
    private Product discountProduct;
    private String coupon;

    public CouponPromotion(Product product,String coupon)
    {
        this.discountProduct = product;
        this.coupon = coupon;
    }

    @Override
    public double apply(List<Product> products,double currentPrice)
    {
        Db db = new Db();

        if(db.CheckCoupon(coupon))
        {
            double discount = discountProduct.getPrice() * 0.3;

            return currentPrice - discount;
        }

        return currentPrice;
    }
}