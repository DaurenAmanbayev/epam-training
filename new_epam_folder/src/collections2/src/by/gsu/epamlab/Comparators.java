package collections2.src.by.gsu.epamlab;


import java.util.Comparator;

public  class Comparators
{
    public static class CompareSegmentNumberToLength implements Comparator<SegmentAndItsNumber>
    {

        @Override
        public int compare(SegmentAndItsNumber t2, SegmentAndItsNumber t1)
        {
            return t2.getSegment().compareTo(t1.getSegment());
        }
    }

    public static class CompareSegmentNumberToNumber implements Comparator<SegmentAndItsNumber>
    {

        @Override
        public int compare(SegmentAndItsNumber t2, SegmentAndItsNumber t1)
        {
            return t1.getNumber()-t2.getNumber();
        }
    }


}
