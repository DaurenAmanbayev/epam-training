package Bll;

/**
 * Created by User on 09.11.2015.
 */
public abstract class AbstractReplace
{
    private StringBuilder row;

    public  String replace(String inRow)
    {
        row=new StringBuilder(inRow);
        while (isReplace(row));

        return row.toString();
    }
   protected  abstract boolean isReplace(StringBuilder row);
}
