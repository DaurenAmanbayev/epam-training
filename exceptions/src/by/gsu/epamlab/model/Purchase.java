package by.gsu.epamlab.model;


public class Purchase
{
    private final String name;
    private final int price;
    private final int number;

    public Purchase(String name, String price, String number) throws CsvExceptions
    {
        if(name.equals("")) throw new CsvExceptions(Constants.NAME, CsvExceptions.StringException.EMPTY);

        this.number = parseNumber(number,Constants.NUMBER);
        this.name = name;
        this.price=parseNumber(price,Constants.PRICE);
    }

    protected int parseNumber(String number,String type) throws CsvExceptions
    {
        int tempNumber;
        try
        {
            tempNumber=Integer.parseInt(number);

        } catch (NumberFormatException e)
        {
            throw new CsvExceptions(type, CsvExceptions.StringException.NOT_PARSE);
        }
        if(tempNumber<0)throw new CsvExceptions(type, CsvExceptions.StringException.NON_POSITIVE);
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

}
