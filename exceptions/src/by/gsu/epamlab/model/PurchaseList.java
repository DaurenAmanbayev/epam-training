package by.gsu.epamlab.model;


import by.gsu.epamlab.bll.Comparators;
import by.gsu.epamlab.bll.FabricPurchase;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class PurchaseList
{

    private List<Purchase> purchases;
    private List<String> badRow;
    private static Comparator comparator=null;

    public PurchaseList(String fileName,String comparatorName)
    {
        this.purchases = new ArrayList<>();
        this.badRow = new ArrayList<>();
        fillingList(fileName);
        this.comparator=Comparators.getComparator(comparatorName);
    }

    public PurchaseList()
    {
        this.purchases = new ArrayList<>();
        this.badRow = new ArrayList<>();
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
                    badRow.add(loadRow+ Constants.DELIMETER+e.getMessage());

                }
            }
        } catch (FileNotFoundException e)
        {
            throw new IllegalArgumentException(Constants.FILE_NOT_FOUND);
        }
        finally
        {
            if(scanner!=null)
            {
                scanner.close();
            }
        }
    }

    public List<String> getBadLine()
    {
        return badRow;
    }

    public List<Purchase> getAll()
    {
        return purchases;
    }

    public Purchase getPurchase(int index)
    {
        if(!isIndex(index))
        {
            throw new IndexOutOfBoundsException(Constants.PURCHASE_NOT_ISNT +index );
        }

        return purchases.get(index);
    }

    private boolean isIndex(int index)
    {
        return index >= 0 && index < purchases.size();
    }

    public void setPurchase(int index, Purchase purchase)
    {
        if(!isIndex(index))  throw new IndexOutOfBoundsException(Constants.PURCHASE_NOT_ISNT +index);

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
            purchases.set(index, purchase);

        }

    }

    public int getSize()
    {
        return purchases.size();
    }

    public void remove(int index) throws IndexOutOfBoundsException
    {
        if(!isIndex(index)){throw new IndexOutOfBoundsException (Constants.PURCHASE_NOT_ISNT + index);}
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
        Collections.sort(purchases, comparator);
    }

    public int find(Purchase purchase)
    {
        sort();
        int position=Collections.binarySearch(purchases,purchase, comparator);
        if(position>=0)
        {
            return position;
        }
        else
        {
            throw new IllegalArgumentException(Constants.IS_NOT_FOUND);
        }

    }
}
