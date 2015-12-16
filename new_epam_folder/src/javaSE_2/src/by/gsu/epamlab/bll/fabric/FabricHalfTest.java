package javaSE_2.src.by.gsu.epamlab.bll.fabric;


import javaSE_2.src.by.gsu.epamlab.bll.readers.CSVReader;
import javaSE_2.src.by.gsu.epamlab.model.*;
import javaSE_2.src.by.gsu.epamlab.model.tests.TestHalf;

import java.sql.Date;

public class FabricHalfTest implements IFabricTest
{
    private String fileName;
    private final static int factor=2;


    public FabricHalfTest(String fileName)
    {
        this.fileName = fileName;
    }

    public  AbstractTest getTestFromFile(String login, String name, String date, String mark)
    {
        return new TestHalf(login,name,date,mark);
    }

    public   AbstractTest getTestFromDB(String login, String name, Date date, int mark)
    {
        return new TestHalf(login,name,date,mark);
    }
    public  IFileReader getReader()
    {
        return new CSVReader(this);
    }

    public String getFileName()
    {
        return this.fileName;
    }

    @Override
    public int getFactor()
    {
        return factor;
    }
}
