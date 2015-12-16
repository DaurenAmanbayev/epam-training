package javaSE_2.src.by.gsu.epamlab.model.tests;


import javaSE_2.src.by.gsu.epamlab.model.AbstractTest;

import java.sql.Date;

public class TestDecimal extends AbstractTest
{
    private final int TEN=10;

    public TestDecimal(String login, String name, String date, String mark)
    {
        int markParse=(int)(Double.parseDouble(mark)*TEN);
        Date dateParse=Date.valueOf(date);
        this.login=login;
        this.name=name;
        this.date=dateParse;
        this.mark=markParse;
    }

    public TestDecimal(String login, String name, Date date, int mark)
    {
        super(login, name, date, mark);
    }

    @Override
    public String toString()
    {
        final String POINT=".";
        return fieldToString()+mark/TEN+POINT+mark%TEN;
    }
}
