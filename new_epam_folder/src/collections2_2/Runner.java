package collections2_2;


import collections2_2.by.gsu.epamlab.Comparators;
import collections2_2.by.gsu.epamlab.NumLen;

import java.util.*;

public class Runner
{//TODO

    final static int COUNT=100000;
    public static void main(String[] args)
    {
        List<Integer> inputList=sortedNum();


        System.out.println("array it is");

        Set<NumLen> numLens=new HashSet<>();
        Date startDate=new Date();
        for(int i:inputList)
        {
            NumLen newElem = new NumLen(i);
            numLens.add(newElem);
        }

        List<NumLen> result=new ArrayList<>();
        result.addAll(numLens);

        Collections.sort(result,new Comparators.CompareNumLenToNum());
        Date stopDate=new Date();
        System.out.println("Time elapsed " +(stopDate.getTime()-startDate.getTime())+ " unick segment "+numLens.size());

    }

    private static List<Integer> sortedUp()
    {
        List<Integer> result=new ArrayList<>();
        int index=1;
        while (index<=COUNT)
        {
            result.add(index++);
        }
        return result;
    }

    private static List<Integer> sortedDown()
    {
        List<Integer> result=new ArrayList<>();
        int index=COUNT;
        while (index>0)
        {
            result.add(index--);
        }
        return result;
    }

    private static List<Integer> unsorted()
    {
        List<Integer> result=new ArrayList<>();
        int index=1;
        while (index<=COUNT)
        {
            result.add(new Random().nextInt());
            index++;
        }
        return result;
    }

    private static List<Integer> sortedNum()
    {
        List<Integer> result=new ArrayList<>();
        int index=1;
        int tmp=1;
        while (index<COUNT)
        {
            int rnd=new Random().nextInt();
            for(int j=1;j<tmp;j++)
            {
                if(result.size()>=COUNT){break;}
                result.add(rnd);
            }
            tmp++;
            index=result.size();

        }
        return result;
    }

    private static List<Integer> nearlySorted()
    {
        List<Integer> result=new ArrayList<>();
        int index=1;
        while (index<COUNT)
        {
            int rnd=new Random().nextInt();
            for(int j=1;j<100;j++)
            {
                result.add(rnd);
                if(index==COUNT){break;}
                index++;
            }
        }
        return result;
    }

}
