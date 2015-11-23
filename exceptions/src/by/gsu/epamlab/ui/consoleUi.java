package by.gsu.epamlab.ui;

import by.gsu.epamlab.model.Constants;
import by.gsu.epamlab.model.Purchase;
import by.gsu.epamlab.model.PurchaseList;

import java.util.List;


public class consoleUi
{

    public static void printTable(PurchaseList purchase)
    {
        System.out.format(Constants.FORMAT_TABLE_STRING, Constants.COLUMN_NAME.split(Constants.SEPARATOR));

         for(Purchase purchaseTmp:purchase.getAll())
            {
                System.out.format(Constants.FORMAT_TABLE_STRING, purchaseTmp.toString().split(Constants.SEPARATOR));
            }

        System.out.format(Constants.FORMAT_STRING_TOTAL, Constants.TOTAL_COST, purchase.getTotalCost());
    }

    public static void printFindPurchase(Purchase purchase,PurchaseList purchaseList)
    {
        int findPurchase;
        System.out.println(Constants.ELEMENT);
        System.out.println(purchase);
        try
        {
            findPurchase = purchaseList.find(purchase);
            System.out.println(Constants.FIND_POSITION +findPurchase);

        } catch (IllegalArgumentException e)
        {
           System.out.println(Constants.PURCHASE_NOT_FOUND);
        }
    }

    public static void printBadLine(List<String> badLine)
    {
        for(String string:badLine)
        {
            System.err.println(string);
        }
    }
}
