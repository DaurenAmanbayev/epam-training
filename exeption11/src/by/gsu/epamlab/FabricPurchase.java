package by.gsu.epamlab;


import java.util.Scanner;

public class FabricPurchase
{
    final static String SEPARATOR=";";

    public static Purchase getPurchase( String loadRow) throws ExampleException
    {
        String[] loadRows=loadRow.split(SEPARATOR);
        switch (loadRows.length)
        {
            case 3:
            {
                return new Purchase(loadRows[0],loadRows[1],loadRows[2]);
            }
            case 4:
            {
                return new PriceDiscountPurchase(loadRows[0],loadRows[1],loadRows[2],loadRows[3]);
            }
            default:
            {
                throw new ExampleException("wrong number of arguments");
            }
        }
    }
}
