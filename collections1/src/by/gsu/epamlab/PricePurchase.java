package by.gsu.epamlab;

import java.util.Scanner;

public class PricePurchase extends Purchase
{
    private int discount;

    public PricePurchase(String commodity, int price, int number, int discount)
    {
        super(commodity, price, number);
        this.discount = discount;
    }
    public PricePurchase(String[] initString)
    {
        this(initString[0],Integer.parseInt(initString[1]),
                Integer.parseInt(initString[2]),Integer.parseInt(initString[3]));

    }

    public PricePurchase()
    {
        super();
        this.discount = 0;
    }

    public PricePurchase(Scanner scanner)
    {
        super(scanner);

        discount=scanner.nextInt();
    }

    @Override
    public int getCost()
    {
        return (getPrice()-discount)*getNumber();
    }


    protected String fieldToString()
    {
        return super.fieldToString()+";"+discount;
    }
}
