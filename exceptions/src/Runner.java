import by.gsu.epamlab.model.CsvExceptions;
import by.gsu.epamlab.model.PriceDiscountPurchase;
import by.gsu.epamlab.model.Purchase;
import by.gsu.epamlab.model.PurchaseList;
import by.gsu.epamlab.ui.consoleUi;

public class Runner
{
    final static String FILE_EXTENSION=".csv";

    public static void main(String[] args) throws CsvExceptions
    {

        Purchase a=new Purchase();
        PriceDiscountPurchase b=new PriceDiscountPurchase();



        /*final String IN_FILE_NAME=args[0]+FILE_EXTENSION;
        final String ADDON_FILE_NAME=args[1]+FILE_EXTENSION;
        final String COMPARATOR_NAME=args[2];*/
       // PurchaseList purchaseList=new PurchaseList(IN_FILE_NAME,COMPARATOR_NAME);
        PurchaseList purchaseList=new PurchaseList("in"+FILE_EXTENSION,"PurchaseComparatorV1");
        purchaseList.printBadRow();
        //PurchaseList purchaseAddon=new PurchaseList(ADDON_FILE_NAME,COMPARATOR_NAME);
        PurchaseList rrr=new PurchaseList();
        PurchaseList purchaseAddon=new PurchaseList("addon"+FILE_EXTENSION,"PurchaseComparatorV1");
        purchaseAddon.printBadRow();

        consoleUi.printTable(purchaseList);
        consoleUi.printTable(purchaseList);
        int lastIndexAddon=purchaseAddon.getSize() - 1;
        purchaseList.addPurchases(0, purchaseAddon.getPurchase(lastIndexAddon));
        purchaseList.addPurchases(1000, purchaseAddon.getPurchase(0));
        purchaseList.setPurchase(2, purchaseAddon.getPurchase(2));
        consoleUi.printTable(purchaseList);


        try
        {
            purchaseList.remove(3);
        } catch (IndexOutOfBoundsException e)
        {
            //e.printStackTrace();
            System.err.println(e.getMessage());
        }try
        {
            purchaseList.remove(10);
        } catch (IndexOutOfBoundsException e)
        {
            //e.printStackTrace();
            System.err.println(e.getMessage());
        }try
        {
            purchaseList.remove(-5);
        } catch (IndexOutOfBoundsException e)
        {
            //e.printStackTrace();
            System.err.println(e.getMessage());
        }


        consoleUi.printTable(purchaseList);

        purchaseList.sort();
        consoleUi.printTable(purchaseList);

        purchaseList.find(purchaseAddon.getPurchase(3));
        purchaseList.find(purchaseAddon.getPurchase(1));

    }
}
