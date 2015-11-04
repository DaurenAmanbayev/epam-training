
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Runner
{
    public  void main(String[] args)
    {
        final String FILE_NAME="in";
        final String SEPARATOR=";";
        final String VALUE="value";
        final String ERROR_LINE="Error-line = ";
        final String FILE_NOT_FOUND="File not found";
        final String FORMAT_STRING_FOR_RESULT="Sum = %1.3f\n";
        final String PATTERN_IS_INDEX="(\\s)*index.*";
        final String PATTERN_FOR_INDEX_INDEX="(\\s)*(index)([1-9][0-9]*)$";
        final String PATTERN_FOR_INDEX_VALUE="([1-9][0-9]*)";

        int badRow=0;
        double result=0;




        Pattern patternIndex=Pattern.compile(PATTERN_FOR_INDEX_INDEX);
        Pattern patternValue=Pattern.compile(PATTERN_FOR_INDEX_VALUE);
        Pattern patternIsIndex=Pattern.compile(PATTERN_IS_INDEX);
        ResourceBundle resourceBundle=null;



        try
        {
            resourceBundle=ResourceBundle.getBundle(FILE_NAME);
            Enumeration<String> keys=resourceBundle.getKeys();
            while (keys.hasMoreElements())
            {
                String key=keys.nextElement();
                Matcher matcherIsIndex=patternIsIndex.matcher(key);
                if (matcherIsIndex.matches())
                {
                    Matcher matcherIndex=patternIndex.matcher(key);
                    if(matcherIndex.matches())
                    {
                        String value=resourceBundle.getString(key).trim();
                        Matcher matcherIndexValue=patternValue.matcher(value);
                        if (matcherIndexValue.matches())
                        {
                            String valueNumber=matcherIndex.group(3)+
                                    matcherIndexValue.group(1);

                            try
                            {
                                String valueString=resourceBundle.getString(VALUE+valueNumber).trim();

                                result+=Double.parseDouble(valueString);
                            }
                            catch (NumberFormatException | MissingResourceException e)
                            {
                                badRow++;//counter bar row in properties file
                            }
                        }
                        else
                        {
                            badRow++;//counter bad row in properties file
                        }
                    }
                    else
                    {
                        badRow++;//counter bad row in properties file
                    }
                }

            }

            System.out.format(Locale.ENGLISH,FORMAT_STRING_FOR_RESULT, result);
            System.out.println(ERROR_LINE+badRow);
        } catch (MissingResourceException e)
        {
            System.err.println(FILE_NOT_FOUND);
        }

    }
}
