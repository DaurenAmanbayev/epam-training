package jdbc.src;


import jdbc.src.by.gsu.epamlab.ConnectionDb;
import jdbc.src.by.gsu.epamlab.NumLen;
import jdbc.src.by.gsu.epamlab.SQLQuerys;

import java.sql.*;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Runner
{
    public final static String RESOURCE_NAME="connectDB";
    public static void main(String[] args)
    {

        List<NumLen> numLens=new ArrayList<>();
        Connection c=null;

       try(SQLQuerys sqlQuerys=new SQLQuerys(ConnectionDb.getConnection()))
       {
           numLens=sqlQuerys.selectFromMyTable();
           for(NumLen tmp: numLens)
           {
               System.out.println(tmp);
           }
           System.out.println(sqlQuerys.deleteAllFrequencies());
           for(NumLen tmp:numLens)
           {
               if(!sqlQuerys.insertIntoFrequencies(tmp))
               {
                   throw new SQLException("In table record not added");
               }
           }
           for(NumLen tmp:sqlQuerys.selectFromFrequencies())
           {
               System.out.println(tmp);
           }

       }
        catch (Exception e)
        {
            e.printStackTrace();

        }
    }


}
