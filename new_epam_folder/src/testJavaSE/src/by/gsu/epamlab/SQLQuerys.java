package testJavaSE.src.by.gsu.epamlab;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLQuerys implements AutoCloseable
{

    private final static String GET_TEST_ID="SELECT idTest FROM tests where name=(?)";
    private final static String CREATE_NEW_TEST="INSERT INTO idTest (name) VALUES (?)";

    private final static String CREATE_NEW_RESULT="INSERT INTO results" +
            "(loginId, testId, dat, mark) VALUES (?,?,?,?)";


    private final static String DELETE_ALL_FROM_FREQUENCIES=
            "DELETE FROM frequencies";
    private final static String INSERT_INTO_FREQUENCIES =
            "INSERT INTO frequencies(Len, Num) VALUES(?, ?)";
    private final static String SELECT_FREQUENCIES_WITH_PARAMETER = "SELECT * FROM frequencies WHERE len>num";

    private static Connection connection;
    private static String LEN_STR="len";
    private static String CNT_STR="cnt";
    private static String NUM_STR="num";


    public SQLQuerys(Connection connection) throws SQLException, ClassNotFoundException
    {
        if (SQLQuerys.connection ==null)
        {
            SQLQuerys.connection = connection;
        }
    }

    public static int getFieldFromTable(String table, String field, String criteria, String name) throws SQLException
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

    public boolean addNewRowInToTable(String table, String field, String value) throws SQLException
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



    /*public List<NumLen> selectFromMyTable()
    {
        List<NumLen> result=new ArrayList<>();
        ResultSet resultSet=null;
        try(PreparedStatement preparedStatement=connection.prepareStatement(SELECT_MY_TABLE_COUNT_AND_GROUP))
        {
            resultSet=preparedStatement.executeQuery();
               while (resultSet.next())
               {
                   int len = resultSet.getInt(LEN_STR);
                   int num = resultSet.getInt(CNT_STR);
                   result.add(new NumLen(len, num));
               }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(resultSet!=null && resultSet.isClosed())
                {
                    resultSet.close();
                }
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        return result;

    }

    public boolean deleteAllFrequencies()
    {
        boolean isDelete=false;
        try(PreparedStatement preparedStatement=connection.prepareStatement(DELETE_ALL_FROM_FREQUENCIES))
        {
            preparedStatement.executeUpdate();
            isDelete=true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return isDelete;
    }

    public boolean insertIntoFrequencies(NumLen numLen)
    {
        boolean isInsert=false;

        try(PreparedStatement prepareStatement=connection.prepareStatement(INSERT_INTO_FREQUENCIES))
        {
            int LEN = 1;
            int NUM = 2;
            prepareStatement.setInt(LEN,numLen.getLen());
            prepareStatement.setInt(NUM,numLen.getNum());
            int count=prepareStatement.executeUpdate();
            if(count>0)
            {
                isInsert=true;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }


        return isInsert;
    }

    public List<NumLen> selectFromFrequencies()
    {
        List<NumLen> numLens=new ArrayList<>();
        ResultSet resultSet=null;

        try(PreparedStatement preparedStatement=connection.prepareStatement(SELECT_FREQUENCIES_WITH_PARAMETER))
        {
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next())
            {
                int len=resultSet.getInt(LEN_STR);
                int num=resultSet.getInt(NUM_STR);
                numLens.add(new NumLen(len,num));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(resultSet!=null && resultSet.isClosed())
                {
                    resultSet.close();
                }
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return numLens;
    }*/

    @Override
    public void close() throws Exception
    {
        if(connection!=null)
        {
            connection.close();
        }
    }
}
