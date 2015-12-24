package javaSE_2.src.by.gsu.epamlab.bll;

import javaSE_2.src.by.gsu.epamlab.bll.DAO.ConnectionDb;
import javaSE_2.src.by.gsu.epamlab.bll.DAO.LoaderDAO;
import javaSE_2.src.by.gsu.epamlab.bll.DAO.ResultDAO;
import javaSE_2.src.by.gsu.epamlab.model.AbstractTest;
import javaSE_2.src.by.gsu.epamlab.model.IFabricTest;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class RunnerLogic
{
    public static String RESOURCE_NAME="ConnectionDb";

    public static void logic(IFabricTest fabricTest)
    {
        try
        {
            LoaderDAO.loadResults(fabricTest);
            ResultDAO resultDAO=new ResultDAO(ConnectionDb.getConnection(),fabricTest);

            Map<String,Double> avgMark=resultDAO.getAvgTest();

            System.out.println("Avg mark from current month\n");

            for (Map.Entry<String,Double> temp:avgMark.entrySet())
            {
                System.out.println(temp.getKey()+ "--"+temp.getValue());
            }


            List<AbstractTest> testsFromMonth=resultDAO.getResultTestAtCurrentMonth();

            System.out.println("Test at current month\n");

            for(AbstractTest test:testsFromMonth)
            {
                System.out.println(test);
            }

            int index=testsFromMonth.size()-1;
            Date latestDate=testsFromMonth.get(index).getDate();
            System.out.println("\nTests at "+latestDate+":\n");
            while (testsFromMonth.get(index).getDate().equals(latestDate))
            {
                System.out.println(testsFromMonth.get(index));
                index--;
            }
            ConnectionDb.close();

        } catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

    }
}
