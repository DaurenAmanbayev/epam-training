package javaSE_2.src.by.gsu.epamlab.bll.DAO;

import testJavaSE.src.Runner1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class ConnectionDb implements AutoCloseable
{
    private static ConnectionDb connectionDb;
    private static Connection connection;
    private String dbUrl;
    private String user;
    private String password;
    private String driver;


    private ConnectionDb() throws ClassNotFoundException, SQLException
    {
        String DB_URL = "dbUrl";
        String PASSWORD = "password";
        String USER = "user";
        String DRIVER = "driver";

        ResourceBundle resourcesBundle=ResourceBundle.getBundle(Runner1.RESOURCE_NAME);
        Enumeration<String> resourcesKey=resourcesBundle.getKeys();
        while (resourcesKey.hasMoreElements())
        {
            String key=resourcesKey.nextElement();
            if (key.compareTo(DB_URL)==0){dbUrl=resourcesBundle.getString(key).trim();}
            if (key.compareTo(PASSWORD)==0){password=resourcesBundle.getString(key).trim();}
            if (key.compareTo(USER)==0){user=resourcesBundle.getString(key).trim();}
            if (key.compareTo(DRIVER)==0){driver=resourcesBundle.getString(key).trim();}
        }

            Class.forName(driver);
            connection= DriverManager.getConnection(dbUrl,user,password);
    }


    public static Connection getConnection() throws SQLException, ClassNotFoundException
    {
        if(connectionDb==null)
        {
            connectionDb=new ConnectionDb();
        }
        return connection;
    }

    @Override
    public void close() throws Exception
    {
        if(connection!=null)
        {
            connection.close();
        }
    }
}
