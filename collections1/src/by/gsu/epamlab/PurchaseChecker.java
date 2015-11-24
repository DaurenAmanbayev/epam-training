package by.gsu.epamlab;


import java.util.Map;

public abstract class PurchaseChecker
{
    public abstract boolean check(Map.Entry<Purchase, WeekDay> mapEntry);
}
