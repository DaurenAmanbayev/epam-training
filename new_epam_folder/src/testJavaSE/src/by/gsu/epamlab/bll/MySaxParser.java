package testJavaSE.src.by.gsu.epamlab.bll;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import testJavaSE.src.by.gsu.epamlab.model.Reader;
import xml.src.by.gsu.epamlab.Test;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MySaxParser extends DefaultHandler implements NewTestAction
{


    @Override
    public void setAction(String test) throws SQLException
    {
        System.out.println("in class");
    }

    private enum Tags
    {RESULTS,STUDENT,LOGIN,TESTS,TEST}

    private boolean isStudent;
    private boolean isLogin;
    private List<Test> results;
    private String login;
    private String thisElement;
    private NewTestAction newTestAction;

    public MySaxParser(NewTestAction newTestAction) throws SQLException
    {
        super();
        this.isStudent=false;
        this.isLogin=false;
        results=new ArrayList<>();
        this.newTestAction=newTestAction;
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
                        newTestAction.setAction(out);
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


}
