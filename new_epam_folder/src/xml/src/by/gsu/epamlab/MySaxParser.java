package xml.src.by.gsu.epamlab;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MySaxParser extends DefaultHandler
{
    private enum Tags
    {RESULTS,STUDENT,LOGIN,TESTS,TEST}

    private boolean isStudent;
    private boolean isLogin;
    private List<Test> results;
    private String login;
    private String thisElement;

    public MySaxParser()
    {
        super();
        this.isStudent=false;
        this.isLogin=false;
        results=new ArrayList<>();
    }

    public List<Test> getResults()
    {
        return results;
    }

    @Override
    public void startDocument() throws SAXException
    {
        super.startDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
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
                    try
                    {
                        Test test = new Test(login,attributes.getValue(0),attributes.getValue(1),attributes.getValue(2));
                        results.add(test);
                    } catch (ParseException e)
                    {
                        e.printStackTrace();
                    }

                }
                break;
            }
        }


    }



    @Override
    public void characters(char[] ch, int start, int length) throws SAXException
    {
        Tags tags=Tags.valueOf(thisElement);
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
        Tags stopElement=Tags.valueOf(localName.toUpperCase());
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


}
