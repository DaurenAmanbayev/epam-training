package testJavaSE.src.by.gsu.epamlab.model;

import testJavaSE.src.by.gsu.epamlab.model.AbstractTest;

import java.sql.Date;


public class TestTask3 extends AbstractTest
{

    public TestTask3(String login, String name, Date date, int mark)
    {
        super(login, name, date, mark);
    }

    @Override
    public void setMark(String mark)
    {
        this.mark=Integer.parseInt(mark);
    }

    @Override
    public String toString()
    {
        return fieldToString()+(double)mark/10;
    }
}
