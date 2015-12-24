package javaSE_2.src.by.gsu.epamlab.bll.readers;


import javaSE_2.src.by.gsu.epamlab.model.AbstractTest;
import javaSE_2.src.by.gsu.epamlab.model.IFileReader;

public class ReaderBuffer implements IFileReader
{
    private AbstractTest buffer;
    private boolean empty = true;
    private volatile boolean hasNext=true;
    private volatile boolean endFile=false;



    public ReaderBuffer() {
        buffer=null;
    }


    public synchronized void setResult (AbstractTest result) {

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        while (!empty ) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }

        empty = false;

        this.buffer = result;
        System.out.println("LOAD from file:  "+ result);
        notifyAll();

    }

    public synchronized void endOfFile()
    {
        endFile=true;
        if(empty){hasNext=false;}
    }


    @Override
    public synchronized AbstractTest getTest()
    {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        while (empty)
        {
            try {
                wait();
            } catch (InterruptedException e){ }

        }
        empty = true;
        notifyAll();
        hasNext=!endFile;
        System.out.println("SET to DB: " + buffer+ " hasNext= "+hasNext);
        return buffer;
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
