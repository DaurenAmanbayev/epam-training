package by.gsu.epamlab.bll;


import by.gsu.epamlab.model.Constants;
import by.gsu.epamlab.model.CsvExceptions;
import by.gsu.epamlab.model.PriceDiscountPurchase;
import by.gsu.epamlab.model.Purchase;

public class FabricPurchase
{

    public static Purchase getPurchase( String loadRow) throws CsvExceptions
    {
        String[] loadRows=loadRow.split(Constants.SEPARATOR);
        switch (loadRows.length)
        {
            case Constants.PURCHASE:
            {
                return new Purchase(loadRows[Constants.INDEX_NAME],
                        loadRows[Constants.INDEX_PRICE],loadRows[Constants.INDEX_NUMBER]);
            }
            case Constants.PRICE_DISCOUNT_PURCHASE:
            {
                return new PriceDiscountPurchase(
                        loadRows[Constants.INDEX_NAME],loadRows[Constants.INDEX_PRICE],
                        loadRows[Constants.INDEX_NUMBER],loadRows[Constants.INDEX_DISCOUNT]);
            }
            default:
            {
                throw new CsvExceptions(CsvExceptions.StringException.WRONG_NUMBER);
            }
        }
    }
}
