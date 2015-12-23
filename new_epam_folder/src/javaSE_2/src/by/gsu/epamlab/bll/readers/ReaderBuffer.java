package javaSE_2.src.by.gsu.epamlab.bll.readers;


import javaSE_2.src.by.gsu.epamlab.model.AbstractTest;
import javaSE_2.src.by.gsu.epamlab.model.IFileReader;

public class ReaderBuffer implements IFileReader
{
    private AbstractTest buffer;
    private boolean empty = true;
    private boolean hasNext=false;



    public ReaderBuffer() {
        buffer=null;

    }


    public synchronized AbstractTest getResult()
    {

        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        empty = true;
        notifyAll();
        System.out.println("SET to DB: " +buffer);
        hasNext=false;
        return buffer;
    }

    public synchronized void setResult (AbstractTest result) {

        while (!empty) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }

        empty = false;

        this.buffer = result;
        hasNext=true;

        notifyAll();
        System.out.println("LOAD from file:  "+ result);

    }




    @Override
    public AbstractTest getTest()
    {
        return getResult();
    }

    @Override
    public boolean hasNext()
    {
        return hasNext;
    }

    @Override
    public void close()
    {

    }
}
