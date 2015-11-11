import by.gsu.epamlab.Model.PurchaseList;

public class Runner
{
    public static void main(String[] args)
    {
        PurchaseList purchaseList=new PurchaseList("in.csv");
        PurchaseList purchaseAddon=new PurchaseList("addon.csv");

        purchaseList.printIntoTable();
        purchaseList.addPurchases(0, purchaseAddon.getPurchase(purchaseAddon.getSize()-1));
        purchaseList.addPurchases(1000, purchaseAddon.getPurchase(0));
        purchaseList.setPurchase(2, purchaseAddon.getPurchase(2));
        purchaseList.printIntoTable();

           /* purchaseList.remove(3);
            purchaseList.remove(10);
            purchaseList.remove(-5);*/

        purchaseList.printIntoTable();
        purchaseList.sort();
        purchaseList.printIntoTable();
        purchaseList.find(purchaseAddon.getPurchase(3));
        purchaseList.find(purchaseAddon.getPurchase(1));

    }
}
