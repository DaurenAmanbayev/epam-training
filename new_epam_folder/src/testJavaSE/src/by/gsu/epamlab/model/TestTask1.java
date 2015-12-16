package testJavaSE.src.by.gsu.epamlab.model;


public class TestTask1 extends AbstractTest
{

    public TestTask1(String login, String name, String  date, String  mark)
    {
        super(login, name, date, mark);
    }

    @Override
    public String toString()
    {
        return fieldToString()+mark/10;
    }
}
