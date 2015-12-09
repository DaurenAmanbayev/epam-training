package testJavaSE.src;



import testJavaSE.src.by.gsu.epamlab.bll.*;
import testJavaSE.src.by.gsu.epamlab.model.AbstractTest;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Date;

public class Runner
{
    public static String RESOURCE_NAME="ConnectionDb";

    public static void main(String[] args) throws FileNotFoundException
    {
        try
        {
            Connection connection= ConnectionDb.getConnection();

            final SQLQuerys sqlQuerys=new SQLQuerys(connection);

            Map<String,Double> rrr=new HashMap<>();

            CSVReader csvReader=new CSVReader(new NewTestAction()
            {
                @Override
                public void setAction(String[] test) throws SQLException
                {
                    AbstractTest test1=FabricTest.getTest("TASK2",test[0],test[1],Date.valueOf(test[2]),test[3]);

                    if(!sqlQuerys.addNewTestResult(test1))
                    {
                        System.out.println("bad add");
                    }
                }
            });

            //csvReader.readFromFile("src/in.csv");

            List<AbstractTest> listMonth=sqlQuerys.getResultTestAtCurrentMonth("TASK1");
            for(AbstractTest tmp:listMonth)
            {
                System.out.println(tmp);
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

    }
}
