package jdbc.src.by.gsu.epamlab;

import jdbc.src.Runner;

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

        ResourceBundle resourcesBundle=ResourceBundle.getBundle(Runner.RESOURCE_NAME);
        Enumeration<String> resourcesKey=resourcesBundle.getKeys();
        while (resourcesKey.hasMoreElements())
        {
            String key=resourcesKey.nextElement();
            if (key.compareTo("dbUrl")==0){dbUrl=resourcesBundle.getString(key).trim();}
            if (key.compareTo("password")==0){password=resourcesBundle.getString(key).trim();}
            if (key.compareTo("user")==0){user=resourcesBundle.getString(key).trim();}
            if (key.compareTo("driver")==0){driver=resourcesBundle.getString(key).trim();}
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
