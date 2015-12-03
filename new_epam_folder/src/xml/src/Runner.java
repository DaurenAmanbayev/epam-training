package xml.src;


import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import xml.src.by.gsu.epamlab.MySaxParser;
import xml.src.by.gsu.epamlab.Test;

import java.io.IOException;
import java.util.List;

public class Runner
{
    public static void main(String[] args)
    {
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            MySaxParser handler = new MySaxParser();
            reader.setContentHandler(handler);

            reader.parse("src/xml/students.xml");
            List<Test> results=handler.getResults();
            for(Test temp:results)
            {
                System.out.println(temp);
            }
        } catch (SAXException | IOException e) {

            e.printStackTrace();
        }


    }
}
