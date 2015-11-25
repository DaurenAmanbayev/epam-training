package collections2.src.by.gsu.epamlab;


public class Segment implements Comparable<Segment>
{
    final private double X1;
    final private double X2;
    final private double Y1;
    final private double Y2;
    final private int length;

    public Segment(double X1, double X2, double Y1, double Y2)
    {
        this.X1 = X1;
        this.X2 = X2;
        this.Y1 = Y1;
        this.Y2 = Y2;
        this.length= length();
    }

    public double getX1()
    {
        return X1;
    }

    public double getX2()
    {
        return X2;
    }

    public double getY1()
    {
        return Y1;
    }

    public double getY2()
    {
        return Y2;
    }

    public int getLength()
    {
        return length;
    }

    private int length()
    {
        int length=0;
        length=(int)Math.round(Math.sqrt ((X1 - X2)*(X1 - X2)+(Y1 - Y2)*(Y1 - Y2)));
        return length;
    }

    @Override
    public int compareTo(Segment segment)
    {
        return segment.getLength()-this.length;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Segment)) return false;

        Segment segment = (Segment) o;

        return length == segment.length;

    }

    @Override
    public int hashCode()
    {
        int result=(int)Math.round(length*X1);
        return result;
    }
}
