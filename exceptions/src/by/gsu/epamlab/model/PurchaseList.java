package by.gsu.epamlab.model;


import by.gsu.epamlab.bll.Comparators;
import by.gsu.epamlab.bll.FabricPurchase;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class PurchaseList
{
    final static String FORMAT_TABLE_STRING="%-12s%-12s%-8s%-12s%-12s%n";
    final static String FORMAT_STRING_TOTAL="%-10s%30d%n";
    final static String TOTAL_COST="Total cost =";
    final static String COLUMN_NAME="Name;Price;Number;Discount;Cost";
    final static String SEPARATOR=";";
    final static String DELIMETER="\t-> ";
    final static String BASIC_NAME_COMPARATOR="by.gsu.epamlab.bll.Comparators$";
    final static String COMPARATOR_NAME="PurchaseComparatorV2";
    final static String STRING_ELEMENT="Element";
    final static String FIND_IN="Find in";
    final static String IS_NOT_FOUND="is not found";
    final static String ROW=" row";
    final static String DONT_REMOVED="Row don`t removed";

    private List<Purchase> purchases;
    private List<String> badRow;
    private Comparator comparator;

    public PurchaseList(String fileName,String comparatorName)
    {
        this.purchases = new ArrayList<Purchase>();
        this.badRow = new ArrayList<String>();
        fillingList(fileName);

        if(!comparatorName.equals(""))
        {
            Class comparatorClass;

            try
            {
                comparatorClass = Class.forName(BASIC_NAME_COMPARATOR+comparatorName);
                Object obj=comparatorClass.newInstance();
                if(comparatorName.equals(COMPARATOR_NAME))
                {
                     comparator =(Comparators.PurchaseComparatorV2)obj;

                }
                else
                {
                     comparator =(Comparators.PurchaseComparatorV1)obj;
                }

            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e)
            {
                e.printStackTrace();
            }
        }

    }

    public PurchaseList()
    {
        this.purchases = new ArrayList<Purchase>();
        this.badRow = new ArrayList<String>();
        this.comparator = null;
    }

    private void fillingList(String fileName)
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
                } catch (CsvExceptions e)
                {
                    badRow.add(loadRow+ DELIMETER+e.getMessage());

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

    public void printBadRow()
    {
        for(String string:badRow)
        {
            System.err.println(string);
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

    public void remove(int index)
    {
        try
        {
            purchases.remove(index);
        } catch (Exception e)
        {
            System.err.println(DONT_REMOVED+e.getMessage());
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
    public void printIntoTable()
    {
        System.out.format(FORMAT_TABLE_STRING, COLUMN_NAME.split(SEPARATOR));

        if (!purchases.isEmpty())
        {
            for(Purchase purchase:purchases)
            {
                System.out.format(FORMAT_TABLE_STRING, purchase.toString().split(SEPARATOR));
            }
        }
        System.out.format(FORMAT_STRING_TOTAL, TOTAL_COST, getTotalCost());
    }

    public void sort()
    {
        if(comparator==null)return;
        Collections.sort(purchases, comparator);
    }

    public void find(Purchase purchase)
    {
        if (comparator==null)return;
        if (purchase==null) return;
        sort();
        int position=Collections.binarySearch(purchases,purchase, comparator);

        System.out.println(STRING_ELEMENT);
        System.out.println(purchase.toString());
        System.out.println(position>=0?FIND_IN+position+ROW:IS_NOT_FOUND);
    }
}
