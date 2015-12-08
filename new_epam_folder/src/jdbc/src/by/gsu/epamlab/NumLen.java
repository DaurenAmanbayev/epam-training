package jdbc.src.by.gsu.epamlab;


public class NumLen
{
    private static String SEPARATOR=";";
    private int num;
    private int len;

    public NumLen(int len)
    {
        this.num++;
        this.len=len;
    }

    public NumLen( int len,int num)
    {
        this.num = num;
        this.len = len;
    }

    public int getNum()
    {
        return num;
    }

    public int getLen()
    {
        return len;
    }

    public void increment()
    {
        this.num++;
    }

    @Override
    public String toString()
    {
        return len+SEPARATOR+num;
    }

}
