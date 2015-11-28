package collections2_2.by.gsu.epamlab;


import java.util.Comparator;

public class Comparators
{//TODO
    public static class CompareNumLenToNum implements Comparator<NumLen>
    {
        @Override
        public int compare(NumLen t2, NumLen t1)
        {
            return t1.getNum()-t2.getNum();
        }
    }
    public static class CompareNumLenToLen implements Comparator<NumLen>
    {
        @Override
        public int compare(NumLen t2, NumLen t1)
        {
            return t1.getLen()-t2.getLen();
        }
    }

}
