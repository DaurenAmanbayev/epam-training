package testJavaSE.src.by.gsu.epamlab.model;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
        final String SEPARATOR=";";
        return login+SEPARATOR+name+SEPARATOR+ this.date+SEPARATOR;
    }

    @Override
    public abstract String toString();

}
