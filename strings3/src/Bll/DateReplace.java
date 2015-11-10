package Bll;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DateReplace
{
    final static String PATTERN_DATE="(\\d{1,2}([/\\.-])\\d{1,2}\\2\\d{2,4})";
    final static String FORMAT_OUT_DATE="MMMM dd,yyyy";

    public static String replace(String inputRow)
    {
        Date dateForReplace;
        Pattern patternDate=Pattern.compile(PATTERN_DATE);

        Matcher matcher=patternDate.matcher(inputRow);

        while(matcher.find())
        {
            String DateSeparator=matcher.group(2);
            String formatInDate = "dd"+DateSeparator+"MM"+DateSeparator+"yy";
            SimpleDateFormat dateFormatRead=new SimpleDateFormat(formatInDate);
            SimpleDateFormat dateFormatSave=new SimpleDateFormat(FORMAT_OUT_DATE,Locale.ENGLISH);

            try
            {
                dateForReplace=dateFormatRead.parse(matcher.group(1));
                String dateSaveString=dateFormatSave.format(dateForReplace);
                inputRow=inputRow.replaceAll(matcher.group(1),dateSaveString);
            } catch (ParseException e)
            {
                System.err.println("parse date imposible");

            }
        }

        return inputRow;
    }


}
