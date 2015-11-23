package by.gsu.epamlab.model;

public class PriceDiscountPurchase extends Purchase
{

    final int discount;

    public PriceDiscountPurchase(String name, String price, String number, String discount) throws CsvExceptions
    {
        super(name, price, number);

        if (parseNumber(discount, Constants.DISCOUNT)<getPrice())
        {
            this.discount = parseNumber(discount,Constants.DISCOUNT);
        } else
        {
            throw new CsvExceptions(Constants.DISCOUNT, CsvExceptions.StringException.NON_CORRECT);
        }
    }

    public PriceDiscountPurchase()
    {
        super();
        discount=0;
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
