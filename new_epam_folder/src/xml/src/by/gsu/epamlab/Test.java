package xml.src.by.gsu.epamlab;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test
{
    private String name;
    private Date date;
    private int mark;
    private SimpleDateFormat dateInput =new SimpleDateFormat("yyyy-mm-dd");
    private SimpleDateFormat dateOutput =new SimpleDateFormat("dd.mm.yyyy");
    public Test(String name, String date, String mark)
    {

        String[] markSplit=mark.split("\\.");
        this.name = name;
        try
        {
            this.date = dateInput.parse(date);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        this.mark = Integer.parseInt(markSplit[0]+markSplit[1]);
    }

    @Override
    public String toString()
    {
        return name+";"+ dateOutput.format(this.date)+";"+(mark/10.0)+"\n";
    }
}
