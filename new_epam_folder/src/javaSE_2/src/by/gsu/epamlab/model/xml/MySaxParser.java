package javaSE_2.src.by.gsu.epamlab.model.xml;


import javaSE_2.src.by.gsu.epamlab.model.AbstractTest;
import javaSE_2.src.by.gsu.epamlab.model.IFabricTest;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class MySaxParser extends DefaultHandler
{
    private enum Tags
    {RESULTS,STUDENT,LOGIN,TESTS,TEST}
    private static String SEPARATOR=";";
    private boolean isStudent;
    private boolean isLogin;
    private String login;
    private String thisElement;
    private List<AbstractTest> result;
    private IFabricTest fabricTest;

    public MySaxParser(IFabricTest fabricTest)
    {
        super();
        this.isStudent=false;
        this.isLogin=false;
        this.fabricTest=fabricTest;
        result=new ArrayList<>();
    }

    @Override
    public void startDocument() throws SAXException
    {
        super.startDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
        final int NAME=0;
        final int DATE=1;
        final int MARK=2;
        thisElement=localName.toUpperCase();
        switch (Tags.valueOf(thisElement))
        {
            case STUDENT:
            {
                isStudent=true;
                break;
            }
            case TEST:
            {
                if(isStudent)
                {
                    AbstractTest test=fabricTest.getTestFromFile(login,attributes.getValue(NAME)
                            ,attributes.getValue(DATE),attributes.getValue(MARK));
                    result.add(test);//setbuffer(test);
                }
                break;
            }
        }


    }



    @Override
    public void characters(char[] ch, int start, int length) throws SAXException
    {
        Tags tags= Tags.valueOf(thisElement);
        switch (tags)
        {
            case LOGIN :
            {
                if(!isLogin)
                {
                    login = new String(ch, start, length);
                    isLogin = true;
                }
                break;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException
    {
        Tags stopElement= Tags.valueOf(localName.toUpperCase());
        switch (stopElement)
        {
            case STUDENT:
            {
                isLogin=false;
                isStudent=false;
                break;
            }
        }

    }

    @Override
    public void endDocument() throws SAXException
    {
        super.endDocument();
    }

    public List<AbstractTest> getResult()
    {
        return result;
    }

}
