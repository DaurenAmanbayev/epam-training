package javaSE_2.src.by.gsu.epamlab.model.tests;


import javaSE_2.src.by.gsu.epamlab.model.AbstractTest;

import java.sql.Date;

public class TestHalf extends AbstractTest
{
    private final int TWO=2;
    public TestHalf(String login, String name, String date, String  mark)
    {
        int markParse=(int)(Double.parseDouble(mark)*TWO);
        Date dateParse=Date.valueOf(date);
        this.login=login;
        this.name=name;
        this.date=dateParse;
        this.mark=markParse;
    }

    public TestHalf(String login, String name, Date date, int mark)
    {
        super(login, name, date, mark);
    }

    @Override
    public String toString()
    {
        final String REST=".5";
        String outMark;
        if(mark%2>0)
        {
            outMark=mark/2+REST;
        }
        else
        {
            outMark=String.valueOf( mark/TWO);
        }
        return fieldToString()+outMark;
    }
}
