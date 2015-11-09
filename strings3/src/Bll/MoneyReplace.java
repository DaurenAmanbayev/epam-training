package Bll;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by User on 09.11.2015.
 */
public class MoneyReplace extends AbstractReplace
{


    @Override
    public boolean isReplace(StringBuilder row)
    {
        final String PATTERN_DATE=".*\\s((\\d{1,3})(\\s+)(\\d{3}\\s+)*).*";

        Pattern patternMoney=Pattern.compile(PATTERN_DATE);

        Matcher matcher=patternMoney.matcher(row);

        if(matcher.find())
        {
            System.out.println(matcher.group());
            return true;


        }


        return false;
    }


}
