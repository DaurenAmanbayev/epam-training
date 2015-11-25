package collections2.src.by.gsu.epamlab;


public class SegmentAndItsNumber
{
    private Segment segment;
    private int number;

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


}
