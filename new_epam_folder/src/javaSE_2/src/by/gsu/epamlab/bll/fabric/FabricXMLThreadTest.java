package javaSE_2.src.by.gsu.epamlab.bll.fabric;


import javaSE_2.src.by.gsu.epamlab.bll.readers.ReaderBuffer;
import javaSE_2.src.by.gsu.epamlab.bll.readers.ReaderCSV;
import javaSE_2.src.by.gsu.epamlab.bll.readers.ReaderXMLThread;
import javaSE_2.src.by.gsu.epamlab.model.AbstractTest;
import javaSE_2.src.by.gsu.epamlab.model.IFabricTest;
import javaSE_2.src.by.gsu.epamlab.model.IFileReader;
import javaSE_2.src.by.gsu.epamlab.model.tests.TestDecimal;
import javaSE_2.src.by.gsu.epamlab.model.tests.TestInt;

import java.sql.Date;

public class FabricXMLThreadTest implements IFabricTest
{
    private String fileName;
    private final static int factor=1;
    private ReaderBuffer fileReader;
    private IFileReader CsvReader;
    private ReaderXMLThread readerCSVThread;


    public FabricXMLThreadTest(String fileName)
    {
        this.fileName = fileName;
        fileReader=new ReaderBuffer();
        CsvReader=new ReaderCSV(this);
        readerCSVThread =new ReaderXMLThread(this,fileReader);
        Thread t=new Thread(readerCSVThread);
        t.start();

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
        return fileReader;
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
