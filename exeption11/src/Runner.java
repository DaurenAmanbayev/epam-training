import by.gsu.epamlab.ExampleException;
import by.gsu.epamlab.Purchase;
import by.gsu.epamlab.PurchaseList;

public class Runner
{
    public static void main(String[] args)
    {
        PurchaseList purchaseList=new PurchaseList("in.csv");
        PurchaseList purchaseAddon=new PurchaseList("addon.csv");

        purchaseList.printIntoTable();
        purchaseList.addPurchases(0, purchaseAddon.getPurchase(purchaseAddon.getSize() - 1));
        purchaseList.addPurchases(1000, purchaseAddon.getPurchase(0));
        purchaseList.replacePurchase(2, purchaseAddon.getPurchase(2));
        purchaseList.printIntoTable();
        try
        {
            purchaseList.remove(3);
            purchaseList.remove(10);
            purchaseList.remove(-5);
        } catch (ExampleException e)
        {
            //e.printStackTrace();
            System.err.println(e.getMessage());
        }
        purchaseList.printIntoTable();

    }
}
