package Bll;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by User on 09.11.2015.
 */
public class MoniesReplace
{
    final static String PATTERN_MONEY="\\b(([1-9][0-9]{0,2}(\\s+[1-9][0-9]{2})*)(\\s*)(\\sbelarusian roubles|\\sblr))";

    public static String replace(String inputRow)
    {
        Pattern patternMoney=Pattern.compile(PATTERN_MONEY);
        Matcher matcher=patternMoney.matcher(inputRow);
        while (matcher.find())
        {
            inputRow=inputRow.replaceAll(matcher.group(1),matcher.group(2).replace(" ","")+matcher.group(5));
        }
        return inputRow;
    }


}
