package testJavaSE.src;



import testJavaSE.src.by.gsu.epamlab.bll.FabricTest;
import testJavaSE.src.by.gsu.epamlab.model.AbstractTest;
import testJavaSE.src.by.gsu.epamlab.bll.ConnectionDb;
import testJavaSE.src.by.gsu.epamlab.bll.SQLQuerys;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Runner
{
    public static String RESOURCE_NAME="ConnectionDb";

    public static void main(String[] args)
    {
        try
        {
            Connection connection= ConnectionDb.getConnection();

            SQLQuerys sqlQuerys=new SQLQuerys(connection);

            Map<String,Double> rrr=new HashMap<>();
            /*rrr=sqlQuerys.getAvgTest();
            for(Map.Entry<String,Double> tt:rrr.entrySet())
            {
                System.out.println(tt.getKey()+" -- "+tt.getValue());
            }*/
            List<AbstractTest> ggg=new LinkedList<>();

                ggg=sqlQuerys.getResultTestAtCurrentMonth("TASK2");

            for(AbstractTest gg:ggg)
            {
                System.out.println(gg);
            }

            /*int login=sqlQuerys.getFieldFromTable("logins","idLogin","name","petrov");
            sqlQuerys.addNewRowInToTable("logins","name","petrov");*/
            //System.out.println("k,nlknl "+login);
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

    }
}
