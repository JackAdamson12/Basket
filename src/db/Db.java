package db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import product.Product;

//Создать лист продуктов
public class Db
{
    private String FileName;

    public String getDb() {
        return FileName;
    }

    public Db() {
        FileName = "src/db/product_list.txt";
    }

    public Db(String FileName) {
        this.FileName = FileName;
    }

    public void input(String code,String name, double price) {

        try {
            FileWriter data = new FileWriter(FileName, true);
            data.write(code + ";" +name + ";" + price + "\n");
            data.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public void show() {
        try {
            File data = new File(FileName);
            Scanner scanner = new Scanner(data);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                System.out.println(line);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Product getByName(String searchName)
    {
        try
        {
            File data = new File(FileName);
            Scanner scanner = new Scanner(data);

            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();

                String[] parts = line.split(";");

                String code = parts[0];
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);

                if(name.equals(searchName))
                {
                    scanner.close();

                    return new Product(code, name, price);
                }
            }

            scanner.close();
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean CheckCoupon(String coupon)
    {
        try
        {
            File data = new File("src/db/coupons.txt");

            Scanner scanner = new Scanner(data);

            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();

                if(line.equals(coupon))
                {
                    scanner.close();

                    return true;
                }
            }

            scanner.close();
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }

        return false;
    }

    public void NewCoupon(String c)
    {
        if(CheckCoupon(c))
        {
            return;
        }

        try {
            FileWriter data = new FileWriter("src/db/coupons.txt", true);
            data.write(c +"\n");
            data.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


