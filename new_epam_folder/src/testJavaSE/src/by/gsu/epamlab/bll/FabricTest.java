package testJavaSE.src.by.gsu.epamlab.bll;


import testJavaSE.src.by.gsu.epamlab.model.AbstractTest;
import testJavaSE.src.by.gsu.epamlab.model.TestTask1;
import testJavaSE.src.by.gsu.epamlab.model.TestTask2;
import testJavaSE.src.by.gsu.epamlab.model.TestTask3;

import java.util.Date;

public class FabricTest
{
    public enum SelectTest
    {
        TASK1
                {
                    @Override
                    AbstractTest getTest(String login, String test, Date date, int mark)
                    {
                        return new TestTask1(login,test,date,mark);
                    }
                },
        TASK2
            {
                @Override
                AbstractTest getTest(String login, String test, Date date, int mark)
                {
                    return new TestTask2(login,test,date,mark);
                }
            },
        TASK3
            {
                @Override
                AbstractTest getTest(String login, String test, Date date, int mark)
                {
                    return new TestTask3(login,test,date,mark);
                }
            };
        abstract AbstractTest getTest(String login, String test, Date date,int mark);
    }
    public static AbstractTest getTest(String typeTest,String login,String test,Date date,int mark)
    {

        SelectTest selectTest= SelectTest.valueOf(typeTest);
        return selectTest.getTest(login, test, date, mark);
    }

}
