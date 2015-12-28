package javaSE_2.src.by.gsu.epamlab.bll.fabric;


import javaSE_2.src.by.gsu.epamlab.bll.readers.ReaderBuffer;
import javaSE_2.src.by.gsu.epamlab.bll.readers.ReaderCSV;
import javaSE_2.src.by.gsu.epamlab.bll.readers.thread.ReaderCSVThread;
import javaSE_2.src.by.gsu.epamlab.model.AbstractTest;
import javaSE_2.src.by.gsu.epamlab.model.IFabricTest;
import javaSE_2.src.by.gsu.epamlab.model.IFileReader;
import javaSE_2.src.by.gsu.epamlab.model.tests.TestHalf;
import javaSE_2.src.by.gsu.epamlab.model.tests.TestInt;

import java.sql.Date;

public class FabricHalfThreadTest extends FabricHalfTest implements IFabricTest
{
    private ReaderBuffer fileReader;


    public FabricHalfThreadTest(String fileName)
    {
        super(fileName);
        String fileName1 = fileName;
        fileReader=new ReaderBuffer();
        IFileReader csvReader = new ReaderCSV(this);
        ReaderCSVThread readerCSVThread = new ReaderCSVThread(csvReader, fileReader);
        Thread t=new Thread(readerCSVThread);
        t.start();

    }

    @Override
    public  IFileReader getReader()
    {
        return fileReader;
    }

}
