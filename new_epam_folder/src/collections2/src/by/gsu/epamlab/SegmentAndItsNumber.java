package collections2.src.by.gsu.epamlab;


public class SegmentAndItsNumber implements Comparable<SegmentAndItsNumber>
{
    private Segment segment;
    private int number=0;

    public SegmentAndItsNumber(Segment segment)
    {
        this.segment = segment;
        this.number++;
    }

    public int getNumber()
    {
        return number;
    }

    public Segment getSegment()
    {
        return this.segment;
    }
    public void incrementSegment()
    {
        this.number++;
    }


    @Override
    public String toString()
    {
        return "("+segment.getLength()+ " ; "+ number+" )";
    }

    @Override
    public int compareTo(SegmentAndItsNumber t)
    {
        return t.getSegment().compareTo(segment);
    }
}
