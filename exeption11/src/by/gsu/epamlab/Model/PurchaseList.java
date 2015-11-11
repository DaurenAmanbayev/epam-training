package by.gsu.epamlab.Model;


import by.gsu.epamlab.Bll.Comparators;
import by.gsu.epamlab.Bll.FabricPurchase;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class PurchaseList
{
    final static String FORMAT_TABLE_STRING="%-12s%-12s%-8s%-12s%-12s%n";
    final static String COLUMN_NAME="Name;Price;Number;Discount;Cost";
    final static String SEPARATOR=";";
    final static String DELIMETER="\t-> ";
    private List<Purchase> purchases=new ArrayList<Purchase>();

    public PurchaseList(String fileName)
    {
        Scanner scanner=null;
        String loadRow;
        try
        {
            scanner=new Scanner(new FileReader(fileName));
            while (scanner.hasNext())
            {
                loadRow=scanner.nextLine();
                try
                {
                    purchases.add(FabricPurchase.getPurchase(loadRow));
                } catch (ExampleException e)
                {
                    System.err.println(loadRow+ DELIMETER+e.getMessage());

                }
            }
        } catch (FileNotFoundException e)
        {
            System.err.println("File not found");

        }
        finally
        {
            if(scanner!=null)
            {
                scanner.close();
            }
        }
    }

    public List<Purchase> getAll()
    {
        return purchases;
    }

    public Purchase getPurchase(int index)
    {
        if(index<0)return null ;
        Purchase purchase=null;
        try
        {
            purchase= purchases.get(index);
        } catch (IndexOutOfBoundsException e)
        {
            //e.printStackTrace();
            System.err.println("Purchase with index " +index +" isn`t");
        }
        return purchase;
    }

    public void setPurchase(int index, Purchase purchase)
    {
        if(purchase==null)return;
        try
        {
            purchases.set(index,purchase);
        } catch (Exception e)
        {
            //e.printStackTrace();
            System.err.println("In list not purchase with index "+ index);
        }
    }

    public void addPurchases(int index,Purchase purchase)
    {
        if(purchase==null) return;
        if(index>purchases.size()-1||index<0)
        {
            purchases.add(purchase);
        }
        else
        {
            purchases.add(index, purchase);

        }

    }
    public int getSize()
    {
        return purchases.size();
    }

    public void remove(int index)//TODO podumat exception
    {
        try
        {
            purchases.remove(index);
        } catch (Exception e)
        {
            System.err.println("Row don`n removed "+e.getMessage());
        }


    }

    public int getTotalCost()
    {
        int totalCost=0;
        if (!purchases.isEmpty())
        {
            for (Purchase purchase:purchases)
            {
                totalCost+=purchase.getTotalPrice();
            }
        }
        return totalCost;
    }
    public void printIntoTable()//TODO delete string in format
    {
        System.out.format(FORMAT_TABLE_STRING, COLUMN_NAME.split(SEPARATOR));

        if (!purchases.isEmpty())
        {
            for(Purchase purchase:purchases)
            {
                System.out.format(FORMAT_TABLE_STRING, purchase.toString().split(SEPARATOR));
            }
        }
        System.out.format("%-10s%30d%n", "Total cost =", getTotalCost());
    }

    public void sort()
    {
        Collections.sort(purchases);
    }

    public void find(Purchase purchase)
    {
        if (purchase==null) return;
        sort();
        int position=Collections.binarySearch(purchases,purchase);

        System.out.println("Element");
        System.out.println(purchase.toString());
        System.out.println(position>=0?"Find in "+position+" row":"is not found");
    }
}
