package javaSE_2.src.by.gsu.epamlab.bll.fabric;


import javaSE_2.src.by.gsu.epamlab.bll.readers.thread.ReaderXMLThread;
import javaSE_2.src.by.gsu.epamlab.bll.readers.ReaderBuffer;
import javaSE_2.src.by.gsu.epamlab.model.AbstractTest;
import javaSE_2.src.by.gsu.epamlab.model.IFabricTest;
import javaSE_2.src.by.gsu.epamlab.model.IFileReader;
import javaSE_2.src.by.gsu.epamlab.model.tests.TestDecimal;
import javaSE_2.src.by.gsu.epamlab.model.tests.TestInt;

import java.sql.Date;

public class FabricDecimalThreadTest extends FabricDecimalTest implements IFabricTest
{
    private ReaderBuffer fileReader;

    public FabricDecimalThreadTest(String fileName)
    {
        super(fileName);
        String fileName1 = fileName;
        fileReader=new ReaderBuffer();
        ReaderXMLThread readerXMLThread = new ReaderXMLThread(this, fileReader);
        Thread t=new Thread(readerXMLThread);
        t.start();

    }
    @Override
    public  IFileReader getReader()
    {
        return fileReader;
    }


}
