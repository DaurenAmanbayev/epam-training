package xml.src;


import org.xml.sax.SAXException;
import xml.src.by.gsu.epamlab.MySaxParser;
import xml.src.by.gsu.epamlab.Result;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Runner
{
    public static void main(String[] args)
    {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = null;
        try
        {
            parser = factory.newSAXParser();
            MySaxParser saxp = new MySaxParser();

            parser.parse(new File("src/xml/students.xml"), saxp);
            List<Result> results=saxp.getResults();
            for(Result temp:results)
            {
                System.out.println(temp);
            }



        } catch (ParserConfigurationException | IOException | SAXException e)
        {
            e.printStackTrace();
        }


    }
}
