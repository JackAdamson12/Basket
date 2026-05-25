package benefit;

import product.Product;
import java.util.List;

public interface Promotion
{
    double apply(List<Product> products,double currentPrice);
}
