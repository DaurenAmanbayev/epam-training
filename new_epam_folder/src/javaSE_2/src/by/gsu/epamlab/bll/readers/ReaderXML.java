package javaSE_2.src.by.gsu.epamlab.bll.readers;

import javaSE_2.src.by.gsu.epamlab.model.AbstractTest;
import javaSE_2.src.by.gsu.epamlab.model.IFabricTest;
import javaSE_2.src.by.gsu.epamlab.model.IFileReader;
import javaSE_2.src.by.gsu.epamlab.model.xml.MySaxParser;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Iterator;

public class ReaderXML implements IFileReader
{
    private Iterator<AbstractTest> results;

    public ReaderXML(IFabricTest fabricTest)
    {

        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            MySaxParser handler = new MySaxParser(fabricTest);

            reader.setContentHandler(handler);

            reader.parse(fabricTest.getFileName());
            results =handler.getResult().iterator();

        } catch (SAXException | IOException e) {

            e.printStackTrace();
        }
    }

    @Override
    public AbstractTest getTest()
    {

        return hasNext()?results.next():null;
    }

    @Override
    public boolean hasNext()
    {
        return results!=null && results.hasNext();
    }

    @Override
    public void close()
    {
        // In this class method close not need

    }
}
