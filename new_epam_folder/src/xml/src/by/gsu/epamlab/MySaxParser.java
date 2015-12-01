package xml.src.by.gsu.epamlab;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class MySaxParser extends DefaultHandler
{
    private enum Tags
    {RESULTS,STUDENT,LOGIN,TESTS,TEST}

    private boolean isNameSpace;
    private boolean isStudent;
    private boolean isLogin;
    private String NAME_SPACE;
    private List<Result> results;
    private Result result;
    private String login;
    private String thisElement;

    public MySaxParser()
    {
        super();
        this.isStudent=false;
        this.isNameSpace =false;
        this.isLogin=false;
        results=new ArrayList<>();
    }

    public List<Result> getResults()
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
        if(!isNameSpace)
        {
            NAME_SPACE =attributes.getLocalName(1).replace("xmlns:","")+":";
            isNameSpace=true;
        }
        String tag=qName.replaceAll(NAME_SPACE,"");
        thisElement=tag.toUpperCase();
        switch (Tags.valueOf(thisElement))
        {
            case STUDENT:
            {
                isStudent=true;
                result=new Result();
                break;
            }
            case TEST:
            {
                if(isStudent)
                {
                    Test test=new Test(attributes.getValue(0),attributes.getValue(1),attributes.getValue(2));
                    result.addTest(test);
                }
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
                if(isLogin){break;}
                login= new String(ch,start,length);
                result.setLogin(login);
                isLogin=true;

                break;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException
    {
        String tag=qName.replaceAll(NAME_SPACE,"");
        String stopElement=tag.toUpperCase();
        if(Tags.valueOf(stopElement).equals(Tags.STUDENT))
        {
            isLogin=false;
            result.setLogin(login);
            results.add(result);
            isStudent=false;
        }

    }

    @Override
    public void endDocument() throws SAXException
    {
        super.endDocument();
    }


}
