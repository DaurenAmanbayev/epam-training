package javaSE_2.src.by.gsu.epamlab.bll.fabric;


import javaSE_2.src.by.gsu.epamlab.bll.readers.ReaderCSV;
import javaSE_2.src.by.gsu.epamlab.model.AbstractTest;
import javaSE_2.src.by.gsu.epamlab.model.IFabricTest;
import javaSE_2.src.by.gsu.epamlab.model.IFileReader;
import javaSE_2.src.by.gsu.epamlab.model.tests.TestInt;

import java.sql.Date;

public class FabricIntTest implements IFabricTest
{
    private String fileName;
    private final static int factor=1;

    public FabricIntTest(String fileName)
    {
        this.fileName = fileName;
    }
    @Override
    public  AbstractTest getTestFromFile(String login, String name, String date, String mark)
    {
        return new TestInt(login,name,date,mark);
    }
    @Override
    public   AbstractTest getTestFromDB(String login, String name, Date date, int mark)
    {
        return new TestInt(login,name,date,mark);
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
        return TestInt.FACTOR;
    }
}
