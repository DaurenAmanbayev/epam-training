package collections2.src;

import collections2.src.by.gsu.epamlab.Comparators;
import collections2.src.by.gsu.epamlab.Segment;
import collections2.src.by.gsu.epamlab.SegmentAndItsNumber;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Runner
{
    final static String PATTERN="\\s*\\(\\s*(.*)\\s*;\\s*(.*)\\s*\\)\\s*\\(\\s*(.*)\\s*;\\s*(.*)\\s*\\)";

    public static void main(String[] args)
    {
        final String FILE_NAME="src/collections2/src/in.txt";
        Pattern pattern=Pattern.compile(PATTERN);
        Scanner scanner=null;

        List<SegmentAndItsNumber> listOfSegments=new ArrayList<>();
        Set<SegmentAndItsNumber> ttt=new TreeSet<>();


        try
        {
            scanner=new Scanner(new FileReader(FILE_NAME));
            while (scanner.hasNext())
            {
                String loadRow=scanner.nextLine();
                Matcher matcher=pattern.matcher(loadRow);
                if(matcher.matches())
                {
                    double X1=Double.parseDouble(matcher.group(1).trim());
                    double Y1=Double.parseDouble(matcher.group(2).trim());
                    double X2=Double.parseDouble(matcher.group(3).trim());
                    double Y2=Double.parseDouble(matcher.group(4).trim());
                    Segment segment=new Segment(X1,X2,Y1,Y2);
                    SegmentAndItsNumber segmentAndItsNumber=new SegmentAndItsNumber(segment);
                    if(ttt.contains(segmentAndItsNumber))
                    {

                    }

                    int found=Collections.binarySearch(listOfSegments, segmentAndItsNumber,new Comparators.CompareSegmentNumberToLength());
                    if(found>=0)
                    {
                        listOfSegments.get(found).incrementSegment();
                    }
                    else
                    {
                        listOfSegments.add(new SegmentAndItsNumber(segment));
                        Collections.sort(listOfSegments,new Comparators.CompareSegmentNumberToLength());
                    }
                }
            }
            Collections.sort(listOfSegments,new Comparators.CompareSegmentNumberToNumber());

            for(SegmentAndItsNumber segmentAndItsNumber :listOfSegments)
            {
                System.out.println(segmentAndItsNumber);
            }

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } finally
        {
            if (scanner!=null)
            {
                scanner.close();
            }
        }
    }
}
