package testJavaSE.src.by.gsu.epamlab.bll;


import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import testJavaSE.src.by.gsu.epamlab.model.AbstractTest;
import testJavaSE.src.by.gsu.epamlab.model.CreateNewRowResults;

import java.io.FileNotFoundException;
import java.io.IOException;
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
        CSVReader readerFile=new CSVReader(new CreateNewRowResults()
        {
            @Override
            public void setAction(String test) throws SQLException
            {
                AbstractTest newTest = createAbstractTest(test);
                if(!sqlQuerys.addNewTestResult(newTest))
                {
                    throw new SQLException("New row doesn't added in table");
                }
            }


        });
        readerFile.readFromFile (fileName);
    }



    private void readFromXML() throws SQLException
    {

        final List<String> xmlToString=new ArrayList<>();

        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            MySaxParser handler = new MySaxParser(new CreateNewRowResults()
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
                AbstractTest newTest = createAbstractTest(string);

                if(!sqlQuerys.addNewTestResult(newTest))
                {
                    throw new SQLException("New row doesn't added in table");
                }
            }

        } catch (SAXException | IOException e) {

            e.printStackTrace();
        }
    }

    public void readAndSave() throws FileNotFoundException, SQLException
    {
        final int EXTENSION_LEN=3;
        String extension=fileName.substring(fileName.length()-EXTENSION_LEN,fileName.length());
        if("csv".equals(extension))
        {
            readFromCSV();
        }
        if("xml".equals(extension))
        {
            readFromXML();
        }

    }

    private AbstractTest createAbstractTest(String test)
    {
        final   String SEPARATOR=";";
        String[] strings=test.split(SEPARATOR);
        final int LOGIN=0;
        final int TEST=1;
        final int DATE=2;
        final int MARK=3;
        String login=strings[LOGIN];
        String testStr=strings[TEST];
        String date=strings[DATE];
        //TODO It's "kostyl", but I don't know how it's will correct
        String mark= String.valueOf((int)(Double.parseDouble(strings[MARK])));
        return FabricTest.getTest(testVersion,login,testStr,date,mark);
    }
}
