package by.gsu.epamlab.Bll;

import by.gsu.epamlab.Model.Purchase;

import java.util.Comparator;


public class Comparators
{
    public static class PurchaseComparatorV1 implements Comparator<Purchase>
    {
        @Override
        public int compare(Purchase t2, Purchase t1)
        {

            if (t2.getName().compareTo(t1.getName()) != 0)
            {
                return t2.getName().compareTo(t1.getName());
            }
            else
            {
                if(t2.getClass()==t1.getClass())
                {
                    return t2.getTotalPrice() - t1.getTotalPrice();
                }
                else
                {
                    return t1.getClass().getSimpleName().compareTo(t2.getClass().getSimpleName());
                }

            }
        }
    }

    public static class PurchaseComparatorV2 implements Comparator<Purchase>
    {
        @Override
        public int compare(Purchase t2, Purchase t1)
        {

            if (t2.getName().compareTo(t1.getName()) != 0)
            {
                return t2.getName().compareTo(t1.getName());
            }
            else
            {
                if(t2.getClass()==t1.getClass())
                {
                    return t2.getTotalPrice() - t1.getTotalPrice();
                }
                else
                {
                    return t1.getClass().getSimpleName().compareTo(t2.getClass().getSimpleName());
                }

            }
        }
    }

}
