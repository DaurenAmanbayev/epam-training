package by.gsu.epamlab.bll;


import by.gsu.epamlab.model.ExampleException;
import by.gsu.epamlab.model.PriceDiscountPurchase;
import by.gsu.epamlab.model.Purchase;

public class FabricPurchase
{
    final static String SEPARATOR=";";
    final static int PURCHASE=3;
    final static int PRICE_DISCOUNT_PURCHASE=4;

    public static Purchase getPurchase( String loadRow) throws ExampleException
    {
        String[] loadRows=loadRow.split(SEPARATOR);
        switch (loadRows.length)
        {
            case PURCHASE:
            {
                return new Purchase(loadRows[0],loadRows[1],loadRows[2]);
            }
            case PRICE_DISCOUNT_PURCHASE:
            {
                return new PriceDiscountPurchase(loadRows[0],loadRows[1],loadRows[2],loadRows[3]);
            }
            default:
            {
                throw new ExampleException(ExampleException.StringException.WRONG_NUMBER);
            }
        }
    }
}
