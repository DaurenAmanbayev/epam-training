package javaSE_2.src.by.gsu.epamlab.bll.readers;


import javaSE_2.src.by.gsu.epamlab.model.AbstractTest;
import javaSE_2.src.by.gsu.epamlab.model.IFileReader;

public class ReaderBuffer implements IFileReader
{
    private AbstractTest buffer;



    public ReaderBuffer() {
        buffer=null;

    }


    public synchronized  void put(AbstractTest value)
    {
        this.buffer=value;
    }

    public synchronized AbstractTest get()
    {
        return buffer;
    }




    @Override
    public AbstractTest getTest()
    {
        return get();
    }

    @Override
    public boolean hasNext()
    {
        return false;
    }

    @Override
    public void close()
    {

    }
}
