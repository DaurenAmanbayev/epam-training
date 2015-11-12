import by.gsu.epamlab.model.PurchaseList;

public class Runner
{
    final static String FILE_EXTENSION=".csv";

    public static void main(String[] args)
    {

        final String IN_FILE_NAME=args[0]+FILE_EXTENSION;
        final String ADDON_FILE_NAME=args[1]+FILE_EXTENSION;
        final String COMPARATOR_NAME=args[2];
        PurchaseList purchaseList=new PurchaseList(IN_FILE_NAME,COMPARATOR_NAME);
        purchaseList.printBadRow();
        PurchaseList purchaseAddon=new PurchaseList(ADDON_FILE_NAME,COMPARATOR_NAME);
        purchaseAddon.printBadRow();

        purchaseList.printIntoTable();
        int lastIndexAddon=purchaseAddon.getSize() - 1;
        purchaseList.addPurchases(0, purchaseAddon.getPurchase(lastIndexAddon));
        purchaseList.addPurchases(1000, purchaseAddon.getPurchase(0));
        purchaseList.setPurchase(2, purchaseAddon.getPurchase(2));
        purchaseList.printIntoTable();

            purchaseList.remove(3);
            purchaseList.remove(10);
            purchaseList.remove(-5);

        purchaseList.printIntoTable();
        purchaseList.sort();
        purchaseList.printIntoTable();
        purchaseList.find(purchaseAddon.getPurchase(3));
        purchaseList.find(purchaseAddon.getPurchase(1));

    }
}
