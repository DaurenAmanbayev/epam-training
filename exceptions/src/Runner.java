import by.gsu.epamlab.model.CsvExceptions;
import by.gsu.epamlab.model.PurchaseList;
import by.gsu.epamlab.ui.consoleUi;

public class Runner
{
    final static String FILE_EXTENSION=".csv";
    final static int INDEX_ZERO=0;
    final static int INDEX_ONE=1;
    final static int INDEX_TWO=2;
    final static int INDEX_THREE=3;
    final static int INDEX_THOUSAND =1000;
    final static int INDEX_TEN =10;
    final static int INDEX_MINUS_FIVE =-5;

    public static void main(String[] args) throws CsvExceptions
    {


        final String IN_FILE_NAME=args[INDEX_ZERO]+FILE_EXTENSION;
        final String ADDON_FILE_NAME=args[INDEX_ONE]+FILE_EXTENSION;
        final String COMPARATOR_NAME=args[INDEX_TWO];
        PurchaseList purchaseList=new PurchaseList(IN_FILE_NAME,COMPARATOR_NAME);
        consoleUi.printBadLine(purchaseList.getBadLine());
        PurchaseList purchaseAddon=new PurchaseList(ADDON_FILE_NAME,COMPARATOR_NAME);
        consoleUi.printBadLine(purchaseAddon.getBadLine());
        consoleUi.printTable(purchaseList);
        int maxIndexAddon=purchaseAddon.getSize() - 1;
        purchaseList.addPurchases(INDEX_ZERO, purchaseAddon.getPurchase(maxIndexAddon));
        purchaseList.addPurchases(INDEX_THOUSAND, purchaseAddon.getPurchase(INDEX_ZERO));
        purchaseList.setPurchase(INDEX_TWO, purchaseAddon.getPurchase(INDEX_TWO));

        consoleUi.printTable(purchaseList);

        purchaseList.sort();
        consoleUi.printTable(purchaseList);

        consoleUi.printFindPurchase(purchaseAddon.getPurchase(1),purchaseList);
        consoleUi.printFindPurchase(purchaseAddon.getPurchase(3),purchaseList);
        try
        {
            purchaseList.remove(INDEX_THREE);
        } catch (IndexOutOfBoundsException e)
        {
            System.err.println(e.getMessage());
        }

        try
        {
            purchaseList.remove(INDEX_TEN);
        } catch (IndexOutOfBoundsException e)
        {
            System.err.println(e.getMessage());
        }

        try
        {
            purchaseList.remove(INDEX_MINUS_FIVE);
        } catch (IndexOutOfBoundsException e)
        {
            System.err.println(e.getMessage());
        }

    }
}
