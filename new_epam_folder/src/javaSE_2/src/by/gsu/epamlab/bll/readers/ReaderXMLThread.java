package javaSE_2.src.by.gsu.epamlab.bll.readers;

import javaSE_2.src.by.gsu.epamlab.model.IFabricTest;
import javaSE_2.src.by.gsu.epamlab.model.xml.MySaxParserForThread;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;


public class ReaderXMLThread implements Runnable
{

    IFabricTest fileReader;
    ReaderBuffer readerBuffer;

    public ReaderXMLThread(IFabricTest fileReader, ReaderBuffer readerBuffer)
    {
        this.fileReader = fileReader;
        this.readerBuffer = readerBuffer;
    }

    @Override
    public void run()
    {

        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            MySaxParserForThread handler = new MySaxParserForThread(fileReader,readerBuffer);

            reader.setContentHandler(handler);

            reader.parse(fileReader.getFileName());


        }
        catch (SAXException e) {

            e.printStackTrace();
        }
        catch (IOException e)
        {//TODO
            System.err.println("File is not found, therefor new date didn't load");
        }

    }
}
