package javaSE_2.src.by.gsu.epamlab.bll.fabric;


import javaSE_2.src.by.gsu.epamlab.bll.readers.ReaderCSV;
import javaSE_2.src.by.gsu.epamlab.model.*;
import javaSE_2.src.by.gsu.epamlab.model.tests.TestHalf;

import java.sql.Date;

public class FabricHalfTest implements IFabricTest
{
    private String fileName;


    public FabricHalfTest(String fileName)
    {
        this.fileName = fileName;
    }
    @Override
    public  AbstractTest getTestFromFile(String login, String name, String date, String mark)
    {
        return new TestHalf(login,name,date,mark);
    }
    @Override
    public   AbstractTest getTestFromDB(String login, String name, Date date, int mark)
    {
        return new TestHalf(login,name,date,mark);
    }
    @Override
    public  IFileReader getReader()
    {
        return new ReaderCSV(this);
    }
    @Override
    public String getFileName()
    {
        return this.fileName;
    }

    @Override
    public int getFactor()
    {
        return TestHalf.FACTOR;
    }
}
