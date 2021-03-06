package by.gsu.epamlab.Model;


import by.gsu.epamlab.Bll.Comparators;

import java.util.Comparator;

public class Purchase implements Comparable<Purchase>
{
    private final static String NAME="Name";
    private final static String PRICE="Price";
    private final static String NUMBER="Number";
    private final String name;
    private final int price;
    private final int number;

    public Purchase(String name, String price, String number) throws ExampleException
    {
        if(name.equals("")) throw new ExampleException(NAME, ExampleException.StringException.EMPTY);

        this.number = parseNumber(number,NUMBER);
        this.name = name;
        this.price=parseNumber(price,PRICE);
    }

    protected int parseNumber(String number,String type) throws ExampleException
    {
        int tempNumber;
        try
        {
            tempNumber=Integer.parseInt(number);

        } catch (NumberFormatException e)
        {
            throw new ExampleException(type, ExampleException.StringException.NOT_PARSE);
        }
        if(tempNumber<0)throw new ExampleException(type, ExampleException.StringException.NON_POSITIVE);
        return tempNumber;
    }

    public Purchase()
    {
        this.name="";
        this.price=0;
        this.number=0;
    }

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

    /*@Override
    public int compare(Purchase t1, Purchase t2)
    {
        return new Comparators.PurchaseComparatorV1().compare(t1, t2);
    }*/

    @Override
    public int compareTo(Purchase purchase)
    {
        if(purchase!=null)
        {
            return new Comparators.PurchaseComparatorV1().compare(this, purchase);
        }
        else
        {
            return new Comparators.PurchaseComparatorV1().compare(this, this);
        }

    }
}
