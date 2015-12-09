package testJavaSE.src.by.gsu.epamlab.model;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractTest
{
    protected String login;
    protected String name;
    protected Date date;
    protected int mark;

    public AbstractTest()
    {
        this.login="";
        this.name = "";
        this.date = null;
        this.mark=0;
    }

    public AbstractTest(String login, String name, Date date, int mark)
    {
        this.login = login;
        this.name = name;
        this.date = date;
        this.mark = mark;
    }

    public AbstractTest(String login, String name, String date) throws ParseException
    {
        final String DATE_IN_FORMAT="yyyy-mm-dd";
        final  SimpleDateFormat INPUT_DATE_FORMAT =new SimpleDateFormat(DATE_IN_FORMAT);
        this.login=login;
        this.name = name;
        this.date = INPUT_DATE_FORMAT.parse(date);
    }

    public String getLogin()
    {
        return login;
    }

    public String getName()
    {
        return name;
    }

    public Date getDate()
    {
        return date;
    }

    public int getMark()
    {
        return mark;
    }

    public abstract void setMark(String mark);

    protected String fieldToString()
    {
        final String DATE_OUT_FORMAT="dd.mm.yyyy";
        final String SEPARATOR=";";
        final  SimpleDateFormat OUTPUT_DATE_FORMAT =new SimpleDateFormat(DATE_OUT_FORMAT);
        return login+SEPARATOR+name+SEPARATOR+ OUTPUT_DATE_FORMAT.format(this.date)+SEPARATOR;
    }

    @Override
    public abstract String toString();

}
