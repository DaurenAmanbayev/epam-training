package testJavaSE.src.by.gsu.epamlab;

public class TestTask1 extends AbstractTest
{

    @Override
    public void setMark(String mark)
    {
        this.mark=Integer.parseInt(mark);
    }

    @Override
    public String toString()
    {
        return fieldToString()+mark;
    }
}
