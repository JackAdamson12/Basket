package db;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class Test_db
{
    @Test
    void input() throws FileNotFoundException
    {
        String testFile = "src/db/test_product.txt";

        Db test = new Db(testFile);

        test.input("00","Banana", 20);

        File file = new File(testFile);

        Scanner scanner = new Scanner(file);

        String line = scanner.nextLine();

        assertEquals("Banana;20", line);

        scanner.close();

        file.delete();
    }
}