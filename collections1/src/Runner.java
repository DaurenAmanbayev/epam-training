import by.gsu.epamlab.PricePurchase;
import by.gsu.epamlab.Purchase;
import by.gsu.epamlab.PurchaseFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Runner
{
    private enum WeekDay{
    SUNDAY,MONDAY, TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY}


    final static String FIRST_PURCHASE="first";
    final static String LAST_PURCHASE="last";
    final static String ANY="any";

    public static void main(String[] args)
    {
        final  String FILE_NAME="src/in.csv";
        Scanner scanner=null;
        Map<Purchase,WeekDay> firstVariant;
        Map<Purchase,WeekDay> secondVariant;
        Map<Purchase,WeekDay> anyVariant;

    }





    private static void printMap(Map<Purchase,WeekDay> purchases)
    {
        for (Map.Entry<Purchase,WeekDay> temp:purchases.entrySet())
        {
            System.out.println(temp.getKey()+" = "+temp.getValue());
        }
    }
}
