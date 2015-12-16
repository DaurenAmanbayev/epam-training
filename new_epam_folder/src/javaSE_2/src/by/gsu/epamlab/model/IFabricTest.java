package javaSE_2.src.by.gsu.epamlab.model;

import java.sql.Date;

public interface IFabricTest
{
    AbstractTest getTestFromFile(String login,String name, String date,String mark);

    AbstractTest getTestFromDB(String login, String name, Date date, int mark);

    IFileReader getReader();

    String getFileName();

    int getFactor();

}
