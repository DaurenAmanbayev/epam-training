package testJavaSE.src.by.gsu.epamlab.model;




public class TestTask2 extends AbstractTest
{

    public TestTask2(String login, String name, String date, String mark)
    {
        super(login, name, date, mark);
    }

    @Override
    public String toString()
    {
        final String POINT=".";
        return fieldToString()+mark/10+POINT+mark%10;
    }
}
