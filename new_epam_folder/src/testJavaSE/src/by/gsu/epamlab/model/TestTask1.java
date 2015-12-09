package testJavaSE.src.by.gsu.epamlab.model;

import testJavaSE.src.by.gsu.epamlab.model.AbstractTest;

import java.sql.Date;


public class TestTask1 extends AbstractTest
{

    public TestTask1(String login, String name, Date date, int mark)
    {
        super(login, name, date, mark);
    }

    @Override
    public String toString()
    {
        return fieldToString()+mark;
    }
}
