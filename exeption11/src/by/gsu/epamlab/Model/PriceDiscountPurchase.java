package by.gsu.epamlab.Model;


public class PriceDiscountPurchase extends Purchase
{
    final static String DISCOUNT="Discount";
    final int discount;

    public PriceDiscountPurchase(String name, String price, String number, String discount) throws ExampleException
    {
        super(name, price, number);

        if (parseNumber(discount, DISCOUNT)<getPrice()*getNumber())
        {
            this.discount = parseNumber(discount,DISCOUNT);
        } else
        {
            throw new ExampleException(DISCOUNT, ExampleException.StringException.NON_CORRECT);
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
