package by.gsu.epamlab.bll;

import by.gsu.epamlab.model.PriceDiscountPurchase;
import by.gsu.epamlab.model.Purchase;

import java.util.Comparator;


public class Comparators
{
    final static String BASIC_NAME_COMPARATOR="by.gsu.epamlab.bll.Comparators$";
    final static String COMPARATOR_NAME="PurchaseComparatorV2";


    public static Comparator<Purchase> getComparator(String comparatorName)
    {
        Comparator comparator=null;
        if(!comparatorName.equals(""))
        {
            Class comparatorClass;

            try
            {
                comparatorClass = Class.forName(BASIC_NAME_COMPARATOR+comparatorName);
                Object obj=comparatorClass.newInstance();
                if(comparatorName.equals(COMPARATOR_NAME))
                {
                    comparator =(PurchaseComparatorV2)obj;
                }
                else
                {
                    comparator =(PurchaseComparatorV1)obj;
                }

            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e)
            {
                e.printStackTrace();

            }
        }
        return comparator;
    }

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

            if (!t2.getName().equals(t1.getName()) )
            {
                return t2.getName().compareTo(t1.getName());
            }
            else
            {
                if(!((t2 instanceof PriceDiscountPurchase)^(t1 instanceof PriceDiscountPurchase)))
                {
                    return t2.getTotalPrice() - t1.getTotalPrice();
                }
                else
                {
                   if((t2 instanceof PriceDiscountPurchase))
                   {
                       return 1;
                   }
                    else
                   {
                       return -1;
                   }
                }

            }
        }
    }

}
