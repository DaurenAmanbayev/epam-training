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
    private final static SimpleDateFormat INPUT_DATE_FORMAT =new SimpleDateFormat("yyyy-mm-dd");
    private final static SimpleDateFormat OUTPUT_DATE_FORMAT =new SimpleDateFormat("dd.mm.yyyy");

    public Test(String login,String name, String date, String mark) throws ParseException
    {

        String[] markSplit=mark.split("\\.");
        this.login=login;
        this.name = name;
        this.date = INPUT_DATE_FORMAT.parse(date);
        this.mark = Integer.parseInt(markSplit[0]+markSplit[1]);
    }

    @Override
    public String toString()
    {
        return login+";"+name+";"+ OUTPUT_DATE_FORMAT.format(this.date)+";"+(mark/10.0);
    }
}
