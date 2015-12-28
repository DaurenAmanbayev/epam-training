package javaSE_2.src.by.gsu.epamlab.bll.fabric;


import javaSE_2.src.by.gsu.epamlab.bll.readers.ReaderXML;
import javaSE_2.src.by.gsu.epamlab.model.*;
import javaSE_2.src.by.gsu.epamlab.model.tests.TestDecimal;

import java.sql.Date;

public class FabricDecimalTest implements IFabricTest
{
    private String fileName;


    public FabricDecimalTest(String fileName)
    {
        this.fileName = fileName;
    }
    @Override
    public  AbstractTest getTestFromFile(String login, String name, String date, String mark)
    {
        return new TestDecimal(login,name,date,mark);
    }
    @Override
    public   AbstractTest getTestFromDB(String login, String name, Date date, int mark)
    {
        return new TestDecimal(login,name,date,mark);
    }
    @Override
    public  IFileReader getReader()
    {
        return new ReaderXML(this);
    }
    @Override
    public String getFileName()
    {
        return this.fileName;
    }

    @Override
    public int getFactor()
    {
        return TestDecimal.FACTOR;
    }
}
