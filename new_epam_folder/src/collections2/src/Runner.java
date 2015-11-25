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
    final static String ARRAY_LIST="ArrayList";
    final static String TREE_SET="TreeSet";


    public static void main(String[] args)
    {
        final String FILE_NAME="src/in.txt";
        final int PATTERN_GROUP_1=1;
        final int PATTERN_GROUP_2=2;
        final int PATTERN_GROUP_3=3;
        final int PATTERN_GROUP_4=4;

        Pattern pattern=Pattern.compile(PATTERN);
        Scanner scanner=null;

        List<SegmentAndItsNumber> listOfSegments=new ArrayList<>();
        Set<SegmentAndItsNumber> treeSetOfSegments=new TreeSet<>(new Comparators.CompareForTreeSet());


        try
        {
            scanner=new Scanner(new FileReader(FILE_NAME));
            while (scanner.hasNext())
            {
                String loadRow=scanner.nextLine();
                Matcher matcher=pattern.matcher(loadRow);
                if(matcher.matches())
                {
                    double X1=Double.parseDouble(matcher.group(PATTERN_GROUP_1).trim());
                    double Y1=Double.parseDouble(matcher.group(PATTERN_GROUP_2).trim());
                    double X2=Double.parseDouble(matcher.group(PATTERN_GROUP_3).trim());
                    double Y2=Double.parseDouble(matcher.group(PATTERN_GROUP_4).trim());
                    Segment segment=new Segment(X1,X2,Y1,Y2);
                    SegmentAndItsNumber segmentAndItsNumber=new SegmentAndItsNumber(segment);
                    SegmentAndItsNumber segmentForTreeSet=new SegmentAndItsNumber(segment);

                    int found=Collections.binarySearch(listOfSegments, segmentAndItsNumber,
                            new Comparators.CompareSegmentNumberToLength());
                    if(found>=0)
                    {
                        listOfSegments.get(found).incrementSegment();
                    }
                    else
                    {
                        listOfSegments.add(segmentAndItsNumber);
                        Collections.sort(listOfSegments,new Comparators.CompareSegmentNumberToLength());
                    }
                    treeSetOfSegments.add(segmentForTreeSet);
                }
            }
            Collections.sort(listOfSegments,new Comparators.CompareSegmentNumberToNumber());

            System.out.println(ARRAY_LIST);

            printList(listOfSegments);

            System.out.println(TREE_SET);

            printSet(treeSetOfSegments);

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

    private static void printList(List<SegmentAndItsNumber> list)
    {
        for(SegmentAndItsNumber segmentAndItsNumber :list)
        {
            System.out.println(segmentAndItsNumber);
        }
    }

    private static void printSet(Set<SegmentAndItsNumber> set)
    {
        for (SegmentAndItsNumber treeSetOfSegment : set)
        {
            System.out.println(treeSetOfSegment);
        }
    }
}
