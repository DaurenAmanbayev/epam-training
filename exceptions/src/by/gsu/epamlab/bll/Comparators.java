package by.gsu.epamlab.bll;

import by.gsu.epamlab.model.Constants;
import by.gsu.epamlab.model.PriceDiscountPurchase;
import by.gsu.epamlab.model.Purchase;

import java.util.Comparator;


public class Comparators
{



    public static Comparator<Purchase> getComparator(String comparatorName)
    {
        Comparator comparator=new Comparators.PurchaseComparatorV1();

        if(!comparatorName.equals(""))
        {
            Class comparatorClass;

            try
            {
                comparatorClass = Class.forName(Constants.BASIC_NAME_COMPARATOR+comparatorName);
                Object obj=comparatorClass.newInstance();
                if(comparatorName.equals(Constants.COMPARATOR_NAME))
                {
                    comparator =(PurchaseComparatorV2)obj;
                }
                else
                {
                    comparator =(PurchaseComparatorV1)obj;
                }

            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e)
            {
                // if will be any exception method return comparator which is created in the first line this method
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
                if(t2 instanceof PriceDiscountPurchase == t1 instanceof PriceDiscountPurchase)
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
