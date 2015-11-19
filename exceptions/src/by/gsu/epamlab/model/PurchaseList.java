package by.gsu.epamlab.model;


import by.gsu.epamlab.bll.Comparators;
import by.gsu.epamlab.bll.FabricPurchase;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class PurchaseList
{
    final static String DELIMETER="\t-> ";
    final static String STRING_ELEMENT="Element";
    final static String FIND_IN="Find in ";
    final static String IS_NOT_FOUND="is not found";
    final static String ROW=" row";
    final static String DONT_REMOVED="Row don`t removed";

    private List<Purchase> purchases;
    private List<String> badRow;
    private static Comparator comparator=null;

    public PurchaseList(String fileName,String comparatorName)
    {
        this.purchases = new ArrayList<Purchase>();
        this.badRow = new ArrayList<String>();
        fillingList(fileName);
        this.comparator=Comparators.getComparator(comparatorName);
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
        if(!isIndex(index))
        {
            throw new IndexOutOfBoundsException("Purchase with index " +index +" isn`t");
        }

        return purchases.get(index);
    }

    private boolean isIndex(int index)
    {
        return index >= 0 && index < purchases.size()-1;
    }
    public void setPurchase(int index, Purchase purchase)
    {
        if(!isIndex(index))  throw new IndexOutOfBoundsException("Purchase with index " +index +" isn`t");

        purchases.set(index,purchase);

    }

    public void addPurchases(int index,Purchase purchase)
    {
        if(!isIndex(index))
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

    public void remove(int index) throws IndexOutOfBoundsException
    {
        if(!isIndex(index)){throw new IndexOutOfBoundsException ("Purchase with index " +index +" isn`t");}
        purchases.remove(index);
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

    @Override
    public String toString()
    {
        StringBuilder outString=new StringBuilder();
        for(Purchase purchase:purchases)
        {
            outString.append(purchase.toString())
                    .append("\n");
        }
        return outString.toString();
    }

    public void sort()
    {
        if(comparator==null)return;
        Collections.sort(purchases, comparator);
    }

    public void find(Purchase purchase)
    {
        if (comparator==null)return;
        sort();
        int position=Collections.binarySearch(purchases,purchase, comparator);

        System.out.println(STRING_ELEMENT);
        System.out.println(purchase.toString());
        System.out.println(position>=0?FIND_IN+position+ROW:IS_NOT_FOUND);
    }
}
