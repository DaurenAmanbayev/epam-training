package testJavaSE.src.by.gsu.epamlab.bll;


import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import testJavaSE.src.by.gsu.epamlab.model.AbstractTest;
import xml.src.by.gsu.epamlab.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReaderFromFileAndSaveToDB
{
    private final SQLQuerys sqlQuerys;
    private final String fileName;
    private final String testVersion;

    public ReaderFromFileAndSaveToDB(SQLQuerys sqlQuerys, String fileName, String testVersion)
    {
        this.sqlQuerys = sqlQuerys;
        this.fileName = fileName;
        this.testVersion = testVersion;
    }

    private void readFromCSV() throws FileNotFoundException, SQLException
    {
        CSVReader readerFile=new CSVReader(new NewTestAction()
        {
            @Override
            public void setAction(String test) throws SQLException
            {
                String[] splitTest=test.split(";");
                AbstractTest test1=FabricTest.getTest(testVersion,splitTest[0],splitTest[1], Date.valueOf(splitTest[2]),splitTest[3]);
                if(!sqlQuerys.addNewTestResult(test1))
                {
                    System.out.println("bad add");
                }
            }


        });
        readerFile.readFromFile (fileName);
    }

    private void readfromXML() throws SQLException
    {
        final List<String> xmlToString=new ArrayList<>();
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            MySaxParser handler = new MySaxParser(new NewTestAction()
            {
                @Override
                public void setAction(String test) throws SQLException
                {
                    xmlToString.add(test);

                }
            });
            reader.setContentHandler(handler);

            reader.parse(fileName);
            for(String string:xmlToString)
            {
                String[] strings=string.split(";");
                AbstractTest test=  FabricTest.getTest(testVersion,strings[0],strings[1], Date.valueOf(strings[2]),strings[3]);
                sqlQuerys.addNewTestResult(test);
            }

        } catch (SAXException | IOException e) {

            e.printStackTrace();
        }
    }

    public void readAndSave() throws FileNotFoundException, SQLException
    {
        String extension=fileName.substring(fileName.length()-3,fileName.length());
        if("csv".equals(extension))
        {
            readFromCSV();
        }
        if("xml".equals(extension))
        {
            readfromXML();
        }

    }
}
