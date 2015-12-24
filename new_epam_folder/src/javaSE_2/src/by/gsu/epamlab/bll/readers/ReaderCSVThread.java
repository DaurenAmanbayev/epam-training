package javaSE_2.src.by.gsu.epamlab.bll.readers;

import javaSE_2.src.by.gsu.epamlab.model.IFileReader;


public class ReaderCSVThread implements Runnable
{

    IFileReader fileReader;
    ReaderBuffer readerBuffer;

    public ReaderCSVThread(IFileReader fileReader, ReaderBuffer readerBuffer)
    {
        this.fileReader = fileReader;
        this.readerBuffer = readerBuffer;
    }

    @Override
    public void run()
    {
        while (fileReader.hasNext())
        {
            readerBuffer.setResult(fileReader.getTest());
            if(!fileReader.hasNext()){ readerBuffer.endOfFile();}

        }

    }
}
