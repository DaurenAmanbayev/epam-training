package by.gsu.epamlab;


public class Purchase
{
    private final String name;
    private final int price;
    private final int number;

    public Purchase(String name, String price, String number) throws ExampleException
    {
        int tempPrice=0;
        int tempNumber=0;
        if(name.equals("")) throw new ExampleException("empty name");

        try
        {
            tempPrice=Integer.parseInt(price);

        } catch (NumberFormatException e)
        {
            throw new ExampleException("Price not parse in int");
        }
        if(tempPrice<0)throw new ExampleException("non positive price");
        try
        {
            tempNumber=Integer.parseInt(number);

        } catch (NumberFormatException e)
        {
            throw new ExampleException("Number not parse in int");
        }
        if(tempNumber<0)throw new ExampleException("non positive number");

        this.number = tempNumber;
        this.name = name;
        this.price=tempPrice;
    }

    /*public Purchase()
    {
        this(null,0,0);
    }*/

    public String getName()
    {
        return name;
    }

    public double getPrice()
    {
        return price;
    }

    public int getNumber()
    {
        return number;
    }
    public int getTotalPrice()
    {
        return number*price;
    }

    protected String fieldToPrint()
    {
        return name+";"+price+";"+number+";";
    }

    @Override
    public String toString()
    {
        return fieldToPrint()+";"+getTotalPrice();
    }

    }
