package collections2.src;

import collections2.src.by.gsu.epamlab.Comparators;
import collections2.src.by.gsu.epamlab.Segment;
import collections2.src.by.gsu.epamlab.SegmentAndItsNumber;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;


public class Runner
{
    final static String PATTERN= "[\\s(;)]+";
    final static String ARRAY_LIST="ArrayList";
    final static String TREE_SET="TreeSet";


    public static void main(String[] args)
    {
        final String FILE_NAME="src/collections2/src/in.txt";
        Scanner scanner=null;

        List<SegmentAndItsNumber> listOfSegments=new ArrayList<>();
        NavigableSet<SegmentAndItsNumber> treeSetOfSegments=new TreeSet<>(new Comparators.CompareForTreeSet());


        try
        {
            scanner=new Scanner(new FileReader(FILE_NAME));
            while (scanner.hasNext())
            {
                String loadRow=scanner.nextLine();
                String[] loadRows=loadRow.split(PATTERN);
                double X1=Double.parseDouble((loadRows[1]).trim());
                double Y1=Double.parseDouble((loadRows[2]).trim());
                double X2=Double.parseDouble((loadRows[3]).trim());
                double Y2=Double.parseDouble((loadRows[4]).trim());
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
                    listOfSegments.add(-found-1,segmentAndItsNumber);

                }

                treeSetOfSegments.add(segmentForTreeSet);


            }
            Collections.sort(listOfSegments,new Comparators.CompareSegmentNumberToNumber());

            System.out.println(ARRAY_LIST);

            printList(listOfSegments);

            System.out.println(TREE_SET);
            List<SegmentAndItsNumber> rrr=new ArrayList<>(treeSetOfSegments.size());
            rrr.addAll(treeSetOfSegments);
            Collections.sort(rrr,new Comparators.CompareSegmentNumberToNumber());

            printList(rrr);

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


}
