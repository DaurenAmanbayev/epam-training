package by.gsu.epamlab.ui;

import by.gsu.epamlab.model.Purchase;
import by.gsu.epamlab.model.PurchaseList;

import java.util.List;

/**
 * Created by User on 16.11.2015.
 */
public class consoleUi
{
    final static String FORMAT_TABLE_STRING="%-12s%-12s%-8s%-12s%-12s%n";
    final static String FORMAT_STRING_TOTAL="%-10s%30d%n";
    final static String TOTAL_COST="Total cost =";
    final static String COLUMN_NAME="Name;Price;Number;Discount;Cost";
    final static String SEPARATOR=";";

    public static void printTable(PurchaseList purchase)
    {
        System.out.format(FORMAT_TABLE_STRING, COLUMN_NAME.split(SEPARATOR));

         for(Purchase purchaseTmp:purchase.getAll())
            {
                System.out.format(FORMAT_TABLE_STRING, purchase.toString().split(SEPARATOR));
            }

        System.out.format(FORMAT_STRING_TOTAL, TOTAL_COST, purchase.getTotalCost());
    }
}
