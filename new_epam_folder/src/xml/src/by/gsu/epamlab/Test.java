package xml.src.by.gsu.epamlab;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test
{
    private String login;
    private String name;
    private Date date;
    private int mark;

    public Test(String login,String name, String date, String mark) throws ParseException
    {
        final String DATE_IN_FORMAT="yyyy-mm-dd";
        final String SEPARATOR="\\.";

        final  SimpleDateFormat INPUT_DATE_FORMAT =new SimpleDateFormat(DATE_IN_FORMAT);

        String[] markSplit=mark.split(SEPARATOR);
        this.login=login;
        this.name = name;
        this.date = INPUT_DATE_FORMAT.parse(date);
        this.mark = Integer.parseInt(markSplit[0]+markSplit[1]);
    }

    @Override
    public String toString()
    {
        final String DATE_OUT_FORMAT="dd.mm.yyyy";
        final double DEVIDER=10.0;
        final String SEPARATOR=";";
        final  SimpleDateFormat OUTPUT_DATE_FORMAT =new SimpleDateFormat(DATE_OUT_FORMAT);
        return login+SEPARATOR+name+SEPARATOR+ OUTPUT_DATE_FORMAT.format(this.date)+SEPARATOR+(mark/DEVIDER);
    }
}
