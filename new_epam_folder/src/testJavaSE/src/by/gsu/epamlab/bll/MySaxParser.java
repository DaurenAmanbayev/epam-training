package testJavaSE.src.by.gsu.epamlab.bll;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import testJavaSE.src.by.gsu.epamlab.model.CreateNewRowResults;

import java.sql.SQLException;

public class MySaxParser extends DefaultHandler implements CreateNewRowResults
{
    private enum Tags
    {RESULTS,STUDENT,LOGIN,TESTS,TEST}

    private boolean isStudent;
    private boolean isLogin;
    private String login;
    private String thisElement;
    private CreateNewRowResults createNewRowResults;

    public MySaxParser(CreateNewRowResults createNewRowResults) throws SQLException
    {
        super();
        this.isStudent=false;
        this.isLogin=false;
        this.createNewRowResults = createNewRowResults;
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
                    try
                    {
                        String out=login+";"+attributes.getValue(NAME)+";"+
                        attributes.getValue(DATE)+";"+attributes.getValue(MARK);
                        createNewRowResults.setAction(out);
                    }catch (SQLException e)
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

    @Override
    public void setAction(String test) throws SQLException
    {
        return;
    }
}
