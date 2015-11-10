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
public class MoneyReplace
{
    final static String PATTERN_MONEY="\\b([1-9][0-9]{0,2}(\\s+[1-9][0-9]{2})*)(\\s*)(\\sbelarusian roubles|\\sblr)";

    public static String replace(String inputRow)
    {
        StringBuilder row=new StringBuilder(inputRow);

        Pattern patternMoney=Pattern.compile(PATTERN_MONEY);
        while (true)
        {
            Matcher matcher=patternMoney.matcher(row);
            if(matcher.find())
            {
                //replace space between monies and  currency monies
                //matcher.group(3)-excess space
                row.replace(matcher.start(3), matcher.start(3) +matcher.group(3).length(), "");
                if(matcher.group(2)!=null)
                {
                    int startPositionMonies=matcher.start(1);
                    int endPositionMonies=matcher.start(1)+ matcher.group(1).length();
                    row.replace(startPositionMonies,endPositionMonies , matcher.group(1).replace(" ", ""));
                    continue;
                }
            }


            break;
        }
        return row.toString();
    }


}
