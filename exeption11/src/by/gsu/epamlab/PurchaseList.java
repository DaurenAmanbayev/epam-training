package by.gsu.epamlab;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PurchaseList
{
    private List<Purchase> purchases=new ArrayList<Purchase>();

    public PurchaseList(String fileName)
    {
        Scanner scanner;
        String loadRow="";
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
                    //e.printStackTrace();
                    System.err.println(loadRow+ "  "+e.getMessage());

                }
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public List<Purchase> getAll()
    {
        return purchases;
    }

    public Purchase getPurchase(int index)
    {
        return purchases.get(index);
    }
    public void replacePurchase(int index, Purchase purchase)
    {
        purchases.set(index,purchase);
    }

    public void addPurchases(int index,Purchase purchase)
    {
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
    public void remove(int index) throws ExampleException
    {
        if(index>=0 && index<purchases.size())
        {
            purchases.remove(index);
        }
        else throw  new ExampleException("Index out of");

    }

    public int getTotalCost()
    {
        int totalCost=0;
        for (Purchase purchase:purchases)
        {
            totalCost+=purchase.getTotalPrice();
        }
        return totalCost;
    }
    public void printIntoTable()
    {
        System.out.format("%-12s%-12s%-8s%-12s%-12s%n","Name","Price","Number","Discount","Cost");
        for(Purchase purchase:purchases)
        {
            System.out.format("%-12s%-12s%-8s%-12s%-12s%n", purchase.toString().split(";"));
        }
        System.out.format("%-10s%30d%n", "Total cost =", getTotalCost());
    }
}
