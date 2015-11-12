package by.gsu.epamlab.model;


public class PriceDiscountPurchase extends Purchase
{
    final static String DISCOUNT="Discount";
    final int discount;

    public PriceDiscountPurchase(String name, String price, String number, String discount) throws CsvExceptions
    {
        super(name, price, number);

        if (parseNumber(discount, DISCOUNT)<getPrice()*getNumber())
        {
            this.discount = parseNumber(discount,DISCOUNT);
        } else
        {
            throw new CsvExceptions(DISCOUNT, CsvExceptions.StringException.NON_CORRECT);
        }
    }

    public int getTotalPrice()
    {
        return super.getTotalPrice()-discount*getNumber();
    }

    @Override
    public String toString()
    {
        return fieldToPrint()+discount+";"+getTotalPrice();
    }
}
