package product;

import db.Db;
public class Product
{
    private String name;
    private double price;
    private String code;
    private double discountPrice;

    public Product()
    {
        code = "P00";
        name = "Empty";
        price = 0f;
        discountPrice = 0f;
    }
    public Product(String code, String name, double price)
    {
        this.code = code;
        this.name = name;
        this.price = price;
        this.discountPrice = price;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    public void setPrice(double price)
    {
        this.price = price;
    }
    public String getName()
    {
        return name;
    }
    public double getPrice()
    {
        return price;
    }
    public String getCode()
    {
        return code;
    }
    @Override
    public boolean equals(Object o)
    {
        if(this == o)
        {
            return true;
        }

        if(o == null || getClass() != o.getClass())
        {
            return false;
        }

        Product product = (Product) o;

        return code.equals(product.code);
    }
    @Override
    public int hashCode()
    {
        return code.hashCode();
    }

    public double getDiscountPrice()
    {
        return discountPrice;
    }
    public void setDiscountPrice(double discountPrice)
    {
        this.discountPrice = discountPrice;
    }


}
