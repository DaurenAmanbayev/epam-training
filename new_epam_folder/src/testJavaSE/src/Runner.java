package testJavaSE.src;



import testJavaSE.src.by.gsu.epamlab.ConnectionDb;
import testJavaSE.src.by.gsu.epamlab.SQLQuerys;

import java.sql.Connection;
import java.sql.SQLException;

public class Runner
{
    public static String RESOURCE_NAME="ConnectionDb";

    public static void main(String[] args)
    {
        try
        {
            Connection connection= ConnectionDb.getConnection();

            SQLQuerys sqlQuerys=new SQLQuerys(connection);

            int login=sqlQuerys.getFieldFromTable("logins","idLogin","name","petrov");
            sqlQuerys.addNewRowInToTable("logins","name","petrov");
            System.out.println("k,nlknl "+login);
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

    }
}
