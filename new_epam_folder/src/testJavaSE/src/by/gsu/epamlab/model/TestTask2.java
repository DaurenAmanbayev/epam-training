package testJavaSE.src.by.gsu.epamlab.model;

import testJavaSE.src.by.gsu.epamlab.model.AbstractTest;

import java.sql.Date;


public class TestTask2 extends AbstractTest
{

    public TestTask2(String login, String name, Date date, int mark)
    {
        super(login, name, date, mark);
    }

    @Override
    public String toString()
    {
        return fieldToString()+(double)mark/10;
    }
}
