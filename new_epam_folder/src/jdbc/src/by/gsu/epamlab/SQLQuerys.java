package jdbc.src.by.gsu.epamlab;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLQuerys implements AutoCloseable
{
    private final static String SELECT_MY_TABLE_COUNT_AND_GROUP=
            "SELECT round(abs(x1-x2)+0.01) as len, count(round(abs(x1-x2)+0.01)) as cnt" +
                    " FROM my_table group by len order by len desc; ";
    private final static String DELETE_ALL_FROM_FREQUENCIES=
            "Delete from frequencies";
    private final static String INSERT_INTO_FREQUENCIES =
            "INSERT INTO frequencies(Len, Num) VALUES(?, ?)";
    private final static String SELECT_FREQUENCIES_WITH_PARAMETER = "SELECT * FROM frequencies WHERE len>num";

    private static Connection connection;

    public SQLQuerys(Connection connection) throws SQLException, ClassNotFoundException
    {
        if (SQLQuerys.connection ==null)
        {
            SQLQuerys.connection = connection;
        }
    }

    public List<NumLen> selectFromMyTable()
    {
        List<NumLen> result=new ArrayList<>();
        ResultSet resultSet=null;
        try(PreparedStatement preparedStatement=connection.prepareStatement(SELECT_MY_TABLE_COUNT_AND_GROUP))
        {
            resultSet=preparedStatement.executeQuery();
               while (resultSet.next())
               {
                   int len = Integer.parseInt(resultSet.getString("len"));
                   int num = Integer.parseInt(resultSet.getString("cnt"));
                   result.add(new NumLen(len, num));
               }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(resultSet!=null)
            {
                try
                {
                    resultSet.close();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
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
            prepareStatement.setInt(1,numLen.getLen());
            prepareStatement.setInt(2,numLen.getNum());
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
                int len=Integer.parseInt(resultSet.getString("len"));
                int num=Integer.parseInt(resultSet.getString("num"));
                numLens.add(new NumLen(len,num));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(resultSet!=null)
            {
                try
                {
                    resultSet.close();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return numLens;
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
