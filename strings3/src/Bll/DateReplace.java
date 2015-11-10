package Bll;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DateReplace extends AbstractReplace
{


    @Override
    public boolean isReplace(StringBuilder row)
    {
        final String PATTERN_DATE="\\b(\\d{1,2}([/\\.-])\\d{1,2}\\2\\d{2,4})";
        final String FORMAT_OUT_DATE="MMMM dd,yyyy";
        Date dateForReplace;

        Pattern patternDate=Pattern.compile(PATTERN_DATE);
        Matcher matcher=patternDate.matcher(row);

        if(matcher.find())
        {
            String DateSeparator=matcher.group(2);
            String formatInDate = "dd"+DateSeparator+"MM"+DateSeparator+"yy";
            SimpleDateFormat dateFormatRead=new SimpleDateFormat(formatInDate);
            SimpleDateFormat dateFormatSave=new SimpleDateFormat(FORMAT_OUT_DATE,Locale.ENGLISH);

            try
            {
                dateForReplace=dateFormatRead.parse(matcher.group(1));
                int replaceStartPosition=matcher.start(1);
                int replaceEndPosition=matcher.start(1)+ matcher.group(1).length();
                String dateSaveString=dateFormatSave.format(dateForReplace);
                row.replace(replaceStartPosition,replaceEndPosition ,dateSaveString );
                return true;
            } catch (ParseException e)
            {
                System.err.println("parse date imposible");

            }
        }
        return false;
    }


}
