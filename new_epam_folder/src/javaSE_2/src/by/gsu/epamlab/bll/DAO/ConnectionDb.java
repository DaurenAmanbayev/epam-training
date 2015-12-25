package javaSE_2.src.by.gsu.epamlab.bll.DAO;

//import testJavaSE.src.Runner1;

import javaSE_2.src.by.gsu.epamlab.bll.RunnerLogic;
import javaSE_2.src.by.gsu.epamlab.model.Constants.Constants;
import javaSE_2.src.by.gsu.epamlab.model.exeption.ConnectionExeption;

import java.sql.*;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class ConnectionDb
{
    private static final String SQL_PROBLEM="Resource closing problem : ";
    private static ConnectionDb connectionDb;
    private static Connection connection;
    private String dbUrl;
    private String user;
    private String password;
    private String driver;


    private ConnectionDb()
    {
        String DB_URL = "dbUrl";
        String PASSWORD = "password";
        String USER = "user";
        String DRIVER = "driver";

        ResourceBundle resourcesBundle=ResourceBundle.getBundle(RunnerLogic.RESOURCE_NAME);
        Enumeration<String> resourcesKey=resourcesBundle.getKeys();
        while (resourcesKey.hasMoreElements())
        {
            String key=resourcesKey.nextElement();
            if (key.compareTo(DB_URL)==0){dbUrl=resourcesBundle.getString(key).trim();}
            if (key.compareTo(PASSWORD)==0){password=resourcesBundle.getString(key).trim();}
            if (key.compareTo(USER)==0){user=resourcesBundle.getString(key).trim();}
            if (key.compareTo(DRIVER)==0){driver=resourcesBundle.getString(key).trim();}
        }

        try
        {
            Class.forName(driver);
            connection= DriverManager.getConnection(dbUrl,user,password);

        } catch (ClassNotFoundException | SQLException e)
        {
            throw new ConnectionExeption(Constants.CONNECTION_FAIL);
        }
    }


    public static Connection getConnection()
    {
        if(connectionDb==null)
        {
            connectionDb=new ConnectionDb();
        }
        return connection;
    }

    public static void close()
    {
        if(connection!=null)
        {
            try
            {
                connection.close();
            } catch (SQLException e)
            {
                System.err.println(SQL_PROBLEM + e);
            }
        }
    }

    public static <T extends Statement> void closeStatement(T ... stat )
    {
        for (T temp: stat)
        {
            if (temp != null)
            {
                try
                {
                    temp.close();
                } catch (SQLException e)
                {
                    System.err.println(SQL_PROBLEM + e);
                }
            }
        }


    }
}
