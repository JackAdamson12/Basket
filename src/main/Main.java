package main;

import benefit.*;

import db.Db;
import order.Order;
import product.Product;
import service.ProductServis;
import sort.ProductNameComparator;
import sort.ProductPriceComparator;

import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Db db = new Db();

        Order order = new Order();

        Scanner scanner = new Scanner(System.in);

        Product p1 = null;
        Product p2 = null;
        Product p3 = null;



        DiscountPromotion discount = new DiscountPromotion();

        discount.setDiscount(0.05);

        order.addPromotion(discount);

        order.addPromotion(new TwoPlusOne());

        order.addPromotion(new CupPromotion());

        System.out.println("Simulation shop cart:\n");

        System.out.println("/s: Show products\n" +
                        "/ab: Add in basket\n" +
                        "/ac: Add coupon\n" +
                        "/rb: Remove from basket\n" +
                        "/sp: Sort by price\n" +
                        "/sn: Sort by name\n" +
                        "/o: Show order\n" +
                        "/save: Save order\n" +
                        "/q: Quick Debug\n" +
                        "/e: Finish program\n"+
                        "/show: Show menu\n");

        while(true)
        {
            String input = scanner.nextLine();

            if(input.equals("/e"))
            {
                break;
            }

            switch (input)
            {
                case "/s":

                    db.show();
                    break;

                case "/ab":

                    System.out.println("Input product name:");

                    String choice = scanner.nextLine();

                    Product p = db.getByName(choice);

                    if(p != null)
                    {
                        order.addProduct(p);

                        System.out.println("Product added");
                    }
                    else
                    {
                        System.out.println("Product not found");
                    }
                    break;

                case "/ac":
                    System.out.println("Input coupon:");

                    String coupon = scanner.nextLine();

                    System.out.println("Choose product:");

                    String productName = scanner.nextLine();

                    Product product = db.getByName(productName);

                    if(product != null)
                    {
                        if(db.CheckCoupon(coupon))
                        {
                            Promotion couponPromotion = new CouponPromotion(product, coupon);

                            order.addPromotion(couponPromotion);

                            double finalPrice = order.ScoreTotalPrice();
                            System.out.println("Coupon activated");

                            System.out.println("New price: " + finalPrice);
                        }
                        else
                        {
                            System.out.println("Coupon not found");
                        }
                    }
                    else
                    {
                        System.out.println("Product not found");
                    }
                    break;

                case "/sp":
                    order.sortProducts(new ProductPriceComparator());

                    System.out.println("Sorted by price");
                    break;

                case "/sn":
                    order.sortProducts(new ProductNameComparator());

                    System.out.println("Sorted by name");
                    break;

                case "/rb":
                    System.out.println("Input product name to remove:");

                    String removeName = scanner.nextLine();

                    Product removeProduct = db.getByName(removeName);

                    if(removeProduct != null)
                    {
                        order.removeProduct(removeProduct);

                        System.out.println("Product removed");
                    }
                    else
                    {
                        System.out.println("Product not found");
                    }
                    break;

                case "/o":
                    order.showProducts();

                    System.out.println("Total: " + order.ScoreTotalPrice());
                    break;

                case "/save":
                    order.saveOrder();

                    System.out.println("Order saved");
                    break;

                case "/q":
                    p1 = db.getByName("PS5");
                    p2 = db.getByName("PS4");
                    p3 = db.getByName("PSGame");

                    order.addProduct(p1);
                    order.addProduct(p2);
                    order.addProduct(p3);

                    System.out.println("Debug products added");

                    break;

                case "/show":

                    System.out.println("/s: Show products\n" +
                                    "/ab: Add in basket\n" +
                                    "/ac: Add coupon\n" +
                                    "/rb: Remove from basket\n" +
                                    "/sp: Sort by price\n" +
                                    "/sn: Sort by name\n" +
                                    "/o: Show order\n" +
                                    "/save: Save order\n" +
                                    "/q: Quick Debug\n" +
                                    "/e: Finish program\n"+
                                    "/show: Show menu\n");

                    break;

                case "/admin":
                    while(true)
                    {
                        System.out.println("/a Add new coupon\n"+
                                        "/p Add new product\n"+
                                        "/sm Show menu\n"+
                                        "/sc Show coupons\n"+
                                        "/e Exit from admin\n");

                        String adm = scanner.nextLine();

                        if(adm.equals("/e"))
                        {
                            break;
                        }

                        switch (adm)
                        {
                            case "/a":
                                System.out.println("Input data:");

                                String cpn = scanner.nextLine();

                                db.NewCoupon(cpn);
                                break;

                            case "/p":
                                System.out.println("Code:");

                                String code = scanner.nextLine();

                                System.out.println("Name");

                                String name = scanner.nextLine();

                                System.out.println("Price:");

                                double price = scanner.nextDouble();

                                scanner.nextLine();

                                db.input(code, name, price);
                                break;

                            case "/sm":
                                db.show();
                                break;

                            case "/sc":
                                db.ShowCoupon();
                                break;

                            default:

                                System.out.println("Wrong command");
                        }
                    }

                    break;

                default:
                    System.out.println("Wrong command");
            }
        }

        ProductServis service = new ProductServis();

        Product expensive = service.getMostExpensive(order.getProducts());

        if(expensive != null)
        {
            System.out.println("Most expensive: " + expensive.getName());
        }

        List<Product> topProducts = service.getNMostExpensive(order.getProducts(), 2);

        System.out.println("Top expensive products:");

        for(int i = 0; i < topProducts.size(); i++)
        {
            System.out.println(topProducts.get(i).getName() + " - " + topProducts.get(i).getPrice());
        }
    }
}
