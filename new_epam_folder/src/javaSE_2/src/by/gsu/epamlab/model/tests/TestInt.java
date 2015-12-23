package javaSE_2.src.by.gsu.epamlab.model.tests;


import javaSE_2.src.by.gsu.epamlab.model.AbstractTest;

import java.sql.Date;

public class TestInt extends AbstractTest
{
    public final static int FACTOR=1;
    public TestInt(String login, String name, String  date, String  mark)
    {
        int markParse=Integer.parseInt(mark);
        Date dateParse=Date.valueOf(date);
        this.login=login;
        this.name=name;
        this.date=dateParse;
        this.mark=markParse;
    }

    public TestInt(String login, String name, Date date, int mark)
    {
        super(login, name, date, mark);
    }

    @Override
    public String toString()
    {
        return fieldToString()+this.mark;
    }


}
