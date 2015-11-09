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
public class DateReplace extends AbstractReplace
{


    @Override
    public boolean isReplace(StringBuilder row)
    {
        final String PATTERN_DATE=".*\\b((\\d{1,2})([/\\.-])(\\d{1,2})([/\\.-])(\\d{2,4})).*";
        Date date=null;
        Pattern patternDate=Pattern.compile(PATTERN_DATE);

        Matcher matcher=patternDate.matcher(row);

        if(matcher.find())
        {
            StringBuilder dateFormatString = getDateFormatString(matcher);


            SimpleDateFormat dateFormatRead=new SimpleDateFormat(dateFormatString.toString());
            SimpleDateFormat dateFormatSave=new SimpleDateFormat("MMMM dd,yyyy",Locale.ENGLISH);

            try
            {
                date=dateFormatRead.parse(matcher.group(1));
                row.replace(matcher.start(1), matcher.start(1) + matcher.group(1).length(), dateFormatSave.format(date));
                return true;
            } catch (ParseException e)
            {
                System.err.println("parse date imposible");

            }


        }


        return false;
    }

    private StringBuilder getDateFormatString(Matcher matcher)
    {
        StringBuilder dateFormatString=new StringBuilder();
        for (int i=0;i<matcher.group(2).length();i++)
        {
            dateFormatString.append("d");
        }
        dateFormatString.append(matcher.group(3));
        for (int i=0;i<matcher.group(4).length();i++)
        {
            dateFormatString.append("M");
        }
        dateFormatString.append(matcher.group(3));
        for (int i=0;i<matcher.group(6).length();i++)
        {
            dateFormatString.append("y");
        }
        return dateFormatString;
    }
}
