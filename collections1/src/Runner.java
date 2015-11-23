import by.gsu.epamlab.PricePurchase;
import by.gsu.epamlab.Purchase;
import by.gsu.epamlab.PurchaseFactory;
import by.gsu.epamlab.PurchaseList;

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
    final static int FIRST_DAY_PURCHASE=1;
    final static int LAST_DAY_PURCHASE=-1;


    public static void main(String[] args)
    {
        final String FILE_NAME="src/in.csv";
        final Purchase BREAD_1550=new Purchase("bread",1550,1);
        final Purchase BREAD_1700=new Purchase("bread",1700,1);

        Map<Purchase,WeekDay> firstDayPurchase=new HashMap<>();
        Map<Purchase,WeekDay> lastDayPurchase=new HashMap<>();
        Map<WeekDay,PurchaseList> weekDayKey=new TreeMap<>();


        Scanner scanner=null;
        try
        {
            scanner=new Scanner(new FileReader(FILE_NAME));
            while (scanner.hasNext())
            {
                Purchase purchase= PurchaseFactory.getPurchase(scanner);
                WeekDay weekDay= WeekDay.valueOf(scanner.nextLine());
                //create map with key- day of week
                if(!weekDayKey.containsKey(weekDay))
                {
                    weekDayKey.put(weekDay,new PurchaseList(purchase));
                }
                else
                {
                    weekDayKey.get(weekDay).addPurchase(purchase);
                }
                //create map with value - fist day purchase
                if(!firstDayPurchase.containsKey(purchase))
                {
                    firstDayPurchase.put(purchase,weekDay);
                }
                else
                {
                    if(firstDayPurchase.get(purchase).ordinal()>weekDay.ordinal())
                    {
                        firstDayPurchase.put(purchase,weekDay);
                    }
                }
                //create map with value - last day purchase
                if(!lastDayPurchase.containsKey(purchase))
                {
                    lastDayPurchase.put(purchase,weekDay);
                }
                else
                {
                    if(lastDayPurchase.get(purchase).ordinal()<weekDay.ordinal())
                    {
                        lastDayPurchase.put(purchase,weekDay);
                    }
                }
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(scanner!=null)
            {
                scanner.close();
            }
        }
        printMapWithList(weekDayKey);
        System.out.println("-----");



        printMap(firstDayPurchase);
        System.out.println("-----");

        printMap(lastDayPurchase);
        System.out.println("-----");
        System.out.println("first "+ firstDayPurchase.get(BREAD_1550)+" last "+ lastDayPurchase.get(BREAD_1550));
        System.out.println("-----");
        System.out.println("first "+ firstDayPurchase.get(BREAD_1700));


        Iterator iterator=firstDayPurchase.entrySet().iterator();
        while (iterator.hasNext())
        {
            Purchase pur=((Map.Entry<Purchase,WeekDay>)iterator.next()).getKey();
            if(pur.equals(BREAD_1550))
            {
                iterator.remove();
            }
        }

        System.out.println("-----");
        printMap(firstDayPurchase);

    }

    private static void printMap(Map<Purchase,WeekDay> purchases)
    {
        for (Map.Entry<Purchase,WeekDay> temp:purchases.entrySet())
        {
            System.out.println(temp.getKey()+" = "+temp.getValue());
        }
    }
    private static void printMapWithList(Map<WeekDay,PurchaseList> purchases)
    {
        for (Map.Entry<WeekDay,PurchaseList> temp:purchases.entrySet())
        {
            System.out.println(temp.getKey()+" = "+temp.getValue());
        }
    }
}
