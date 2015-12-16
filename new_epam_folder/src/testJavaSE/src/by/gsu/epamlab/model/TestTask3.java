package testJavaSE.src.by.gsu.epamlab.model;


public class TestTask3 extends AbstractTest
{
    public TestTask3(String login, String name, String date, String  mark)
    {
        super(login, name, date, mark);
    }

    @Override
    public String toString()
    {
        final String POINT=".";
        String outMark;
        if(mark%10>0)
        {
            outMark=mark/10+POINT+mark%10;
        }
        else
        {
            outMark=String.valueOf( mark/10);
        }
        return fieldToString()+outMark;
    }
}
