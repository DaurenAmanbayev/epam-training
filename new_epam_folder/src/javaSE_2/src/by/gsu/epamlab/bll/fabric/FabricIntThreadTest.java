package javaSE_2.src.by.gsu.epamlab.bll.fabric;


import javaSE_2.src.by.gsu.epamlab.bll.readers.ReaderBuffer;
import javaSE_2.src.by.gsu.epamlab.bll.readers.ReaderCSV;
import javaSE_2.src.by.gsu.epamlab.bll.readers.thread.ReaderCSVThread;
import javaSE_2.src.by.gsu.epamlab.model.IFabricTest;
import javaSE_2.src.by.gsu.epamlab.model.IFileReader;

public class FabricIntThreadTest extends FabricIntTest implements IFabricTest
{
    private ReaderBuffer fileReader;


    public FabricIntThreadTest(String fileName)
    {
        super(fileName);
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
