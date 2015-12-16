package javaSE_2.src.by.gsu.epamlab.bll.DAO;

import javaSE_2.src.by.gsu.epamlab.model.AbstractTest;
import javaSE_2.src.by.gsu.epamlab.model.IFabricTest;
import javaSE_2.src.by.gsu.epamlab.model.IFileReader;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ResultDAO
{

    private final static SimpleDateFormat ONLY_MONTH =new SimpleDateFormat("MM");
    private final static SimpleDateFormat ONLY_YEAR=new SimpleDateFormat("yyyy");

    private static Connection connection;
    private  IFabricTest fabricTest;



    public ResultDAO(Connection connection, IFabricTest fabricTest) throws SQLException, ClassNotFoundException
    {
        if (ResultDAO.connection ==null)
        {
            ResultDAO.connection = connection;
        }
        this.fabricTest=fabricTest;
    }

    public Map<String,Double> getAvgTest() throws SQLException
    {
        ResultSet resultSet=null;
        Map<String,Double> result=new TreeMap<>();
        final String GET_AVG_RESULT_FROM_MONTH="SELECT logins.name, ROUND(Avg(results.mark/?),2) AS 'avg' " +
                " FROM results INNER JOIN logins ON logins.idLogin = results.loginId " +
                " WHERE MONTH(results.dat)=? AND YEAR(results.dat)=?" +
                " GROUP BY results.loginId" +
                " ORDER BY Avg(results.mark);";

        try(PreparedStatement preparedStatement=connection.prepareStatement(GET_AVG_RESULT_FROM_MONTH))
        {
            java.util.Date now=new java.util.Date();
            int month=Integer.parseInt(ONLY_MONTH.format(now));
            int year=Integer.parseInt(ONLY_YEAR.format(now));

            preparedStatement.setInt(1,fabricTest.getFactor());
            preparedStatement.setInt(2,month);
            preparedStatement.setInt(3,year);
            resultSet=preparedStatement.executeQuery();

            while (resultSet.next())
            {
                result.put(resultSet.getString(1),resultSet.getDouble(2));
            }
        }
        finally
        {
            if(resultSet!=null)
            {
                resultSet.close();
            }

        }
        return result;
    }

    public List<AbstractTest> getResultTestAtCurrentMonth() throws SQLException
    {
        List<AbstractTest> result=new LinkedList<>();
        ResultSet resultSet=null;
        final  String GET_FIELD_FROM_TABLE ="SELECT logins.name , tests.name, results.dat, results.mark" +
                " FROM tests inner JOIN" +
                " (logins inner JOIN results ON logins.idLogin = results.loginId) ON tests.idTest = results.testId" +
                " WHERE Month(results.dat)=? AND YEAR(results.dat)=?" +
                " ORDER BY results.dat;";

        try(PreparedStatement preparedStatement=connection.prepareStatement(GET_FIELD_FROM_TABLE))
        {
            java.util.Date now=new java.util.Date();
            int month=Integer.parseInt(ONLY_MONTH.format(now));
            int year=Integer.parseInt(ONLY_YEAR.format(now));

            preparedStatement.setInt(1,month);
            preparedStatement.setInt(2,year);
            resultSet=preparedStatement.executeQuery();

            while (resultSet.next())
            {
                String login=resultSet.getString(1);
                String test=resultSet.getString(2);
                Date date=resultSet.getDate(3);
                int mark=resultSet.getInt(4);
                AbstractTest testIn=fabricTest.getTestFromDB(login,test,date,mark);
                result.add(testIn);
            }
        }
        finally
        {
            if(resultSet!=null)
            {
                resultSet.close();
            }

        }
        return result;
    }

}
