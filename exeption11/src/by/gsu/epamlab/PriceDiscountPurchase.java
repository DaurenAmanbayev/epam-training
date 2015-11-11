package by.gsu.epamlab;


public class PriceDiscountPurchase extends Purchase
{
    final int discount;

    public PriceDiscountPurchase(String name, String price, String number, String discount) throws ExampleException
    {
        super(name, price, number);
        int tempDiscount=0;
        try
        {
            tempDiscount=Integer.parseInt(discount);

        } catch (NumberFormatException e)
        {
            throw new ExampleException("Discount not parse in double");
        }
        if(tempDiscount<0)throw new ExampleException("non positive discount");
        if(super.getTotalPrice()<tempDiscount*super.getNumber())throw new ExampleException("Discount it is more than cost");
        this.discount = tempDiscount;
    }

    public int getTotalPrice()
    {
        return super.getTotalPrice()-discount*super.getNumber();
    }

    @Override
    public String toString()
    {
        return super.fieldToPrint()+discount+";"+getTotalPrice();
    }
}
