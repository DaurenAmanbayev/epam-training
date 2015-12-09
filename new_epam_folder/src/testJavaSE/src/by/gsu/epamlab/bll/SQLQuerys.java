package testJavaSE.src.by.gsu.epamlab.bll;

import testJavaSE.src.by.gsu.epamlab.model.AbstractTest;
import java.sql.Date;
import java.util.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class SQLQuerys implements AutoCloseable
{
    private final static String CREATE_NEW_RESULT="INSERT INTO results" +
            "(loginId, testId, dat, mark) VALUES (?,?,?,?)";
    private final static SimpleDateFormat ONLY_MONTH =new SimpleDateFormat("MM");

    private static Connection connection;





    public SQLQuerys(Connection connection) throws SQLException, ClassNotFoundException
    {
        if (SQLQuerys.connection ==null)
        {
            SQLQuerys.connection = connection;
        }
    }

    private int getFieldFromTable(String table, String field, String criteria, String name) throws SQLException
    {
        int loginId=0;
        ResultSet resultSet=null;
        final  String GET_FIELD_FROM_TABLE ="SELECT "+field+" FROM " +table+" where "+criteria+"=(?)";

        try(PreparedStatement preparedStatement=connection.prepareStatement(GET_FIELD_FROM_TABLE))
        {
            preparedStatement.setString(1,name);
            resultSet=preparedStatement.executeQuery();

            while (resultSet.next())
            {
                loginId=resultSet.getInt(1);
            }
        }
        finally
        {
            if(resultSet!=null)
            {
                resultSet.close();
            }

        }
        return loginId;
    }

    private boolean addNewRowInToTable(String table, String field, String value) throws SQLException
    {
        boolean isCreate=false;
        final  String CREATE_NEW_LOGIN="INSERT INTO "+table+" ("+field+") VALUES (?)";

        int count;
        try(PreparedStatement preparedStatement=connection.prepareStatement(CREATE_NEW_LOGIN))
        {
            preparedStatement.setString(1,value);
            count=preparedStatement.executeUpdate();
            if(count>0)
            {
                isCreate=true;
            }
        }

        return isCreate;

    }

    public  boolean addNewTestResult(AbstractTest test) throws SQLException
    {
        String ID_TEST = "idTest";
        String TEST = "tests";
        String NAME = "name";
        String ID_LOGIN = "idLogin";
        String LOGIN = "logins";

        boolean isAdd=false;
        int idLogin;
        int idTest;
        int count;
        Date dateSQL=test.getDate();


        if((idLogin=getFieldFromTable(LOGIN, ID_LOGIN, NAME,test.getLogin()))<=0)
        {
            addNewRowInToTable(LOGIN, NAME,test.getName());
            idLogin=getFieldFromTable(LOGIN, ID_LOGIN, NAME,test.getLogin());
        }

        if((idTest=getFieldFromTable(TEST, ID_TEST, NAME,test.getName()))<=0)
        {
            addNewRowInToTable(TEST, NAME,test.getName());
            idTest=getFieldFromTable(TEST, ID_TEST, NAME,test.getName());
        }
        try(PreparedStatement preparedStatement=connection.prepareStatement(CREATE_NEW_RESULT))
        {
            preparedStatement.setInt(1,idLogin);
            preparedStatement.setInt(2,idTest);
            preparedStatement.setDate(3,dateSQL);
            preparedStatement.setInt(4,test.getMark());

            count=preparedStatement.executeUpdate();
            if(count>0)
            {
                isAdd=true;
            }
        }
        return isAdd;
    }

    public Map<String,Double> getAvgTest() throws SQLException
    {
        ResultSet resultSet=null;
        Map<String,Double> result=new TreeMap<>();
        final String GET_AVG_RESULT_FROM_MONTH="SELECT logins.name, Avg(results.mark) AS 'avg' " +
                " FROM results INNER JOIN logins ON logins.idLogin = results.loginId " +
                " WHERE MONTH(results.dat)=? AND YEAR(results.dat)=?" +
                " GROUP BY results.loginId" +
                " ORDER BY Avg(results.mark);";

        try(PreparedStatement preparedStatement=connection.prepareStatement(GET_AVG_RESULT_FROM_MONTH))
        {
            java.util.Date now=new java.util.Date();
            int month=Integer.parseInt(ONLY_MONTH.format(now));
            SimpleDateFormat onlyYear=new SimpleDateFormat("yyyy");
            int year=Integer.parseInt(onlyYear.format(now));

            preparedStatement.setInt(1,month);
            preparedStatement.setInt(2,year);
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

    public List<AbstractTest> getResultTestAtCurrentMonth(String typeOfTest) throws SQLException
    {
        List<AbstractTest> result=new LinkedList<>();
        ResultSet resultSet=null;
        final  String GET_FIELD_FROM_TABLE ="SELECT logins.name , tests.name, results.dat, results.mark" +
                " FROM tests right JOIN" +
                " (logins right JOIN results ON logins.idLogin = results.loginId) ON tests.idTest = results.testId" +
                " WHERE Month(results.dat)=?" +
                " ORDER BY results.dat;";

        try(PreparedStatement preparedStatement=connection.prepareStatement(GET_FIELD_FROM_TABLE))
        {
            java.util.Date now=new java.util.Date();
            int month=Integer.parseInt(ONLY_MONTH.format(now));
            preparedStatement.setInt(1,month);
            resultSet=preparedStatement.executeQuery();

            while (resultSet.next())
            {
                String login=resultSet.getString(1);
                String test=resultSet.getString(2);
                Date date=resultSet.getDate(3);
                String mark=resultSet.getString(4);
                AbstractTest testIn=FabricTest.getTest(typeOfTest,login,test,date,mark);
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



    @Override
    public void close() throws Exception
    {
        if(connection!=null)
        {
            connection.close();
        }
    }
}
