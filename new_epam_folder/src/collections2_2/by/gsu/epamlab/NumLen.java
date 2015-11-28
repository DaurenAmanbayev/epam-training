package collections2_2.by.gsu.epamlab;


public class NumLen implements Comparable<NumLen>
{//TODO
    private int num;
    private int len;

    public NumLen(int len)
    {
        this.num++;
        this.len=len;
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
        return "("+len+";"+num+")";
    }

    @Override
    public int compareTo(NumLen t)
    {
        int diff=len-t.getLen();
        if(diff==0){t.increment();}
        return diff;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof NumLen)) return false;

        NumLen numLen = (NumLen) o;

        if (len != numLen.len) return false;
        numLen.increment();
        return true;

    }

    @Override
    public int hashCode()
    {
        int result = num;
        result = 31 * result + len;
        return result;
    }
}
