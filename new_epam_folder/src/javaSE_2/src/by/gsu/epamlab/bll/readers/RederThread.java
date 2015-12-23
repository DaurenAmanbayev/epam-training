package javaSE_2.src.by.gsu.epamlab.bll.readers;

import javaSE_2.src.by.gsu.epamlab.model.IFileReader;

/**
 * Created by User on 23.12.2015.
 */
public class RederThread implements Runnable
{

    IFileReader fileReader;
    ReaderBuffer readerBuffer;

    public RederThread(IFileReader fileReader, ReaderBuffer readerBuffer)
    {
        this.fileReader = fileReader;
        this.readerBuffer = readerBuffer;
    }

    @Override
    public void run()
    {
        while (fileReader.hasNext())
        {
            readerBuffer.put(fileReader.getTest());
        }
    }
}
