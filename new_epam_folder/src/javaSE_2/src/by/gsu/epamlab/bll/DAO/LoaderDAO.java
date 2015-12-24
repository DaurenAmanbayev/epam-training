package javaSE_2.src.by.gsu.epamlab.bll.DAO;

import javaSE_2.src.by.gsu.epamlab.model.AbstractTest;
import javaSE_2.src.by.gsu.epamlab.model.IFabricTest;
import javaSE_2.src.by.gsu.epamlab.model.IFileReader;

import java.sql.*;


public class LoaderDAO
{
    private static int getId(PreparedStatement getFromTable,PreparedStatement addToTable, String name) throws SQLException
    {
        int loginId=0;
        getFromTable.setString(1,name);

        ResultSet resultSet=getFromTable.executeQuery();
        if(resultSet.first())
        {
            loginId=resultSet.getInt(1);
        }
        else
        {
           loginId=addNewRow(addToTable,name);
        }
        return loginId;
    }

    private static int addNewRow(PreparedStatement addToTable, String value) throws SQLException
    {
        int count;
        int addingId=0;
        addToTable.setString(1,value);
        count=addToTable.executeUpdate();
        if(count>0)
        {
            ResultSet resultSet=addToTable.getGeneratedKeys();
            resultSet.first();
            addingId=resultSet.getInt(1);
        }
        else
        {
            throw new SQLException("New row didn't added");
        }


        return addingId;

    }

    public static void loadResults(IFabricTest fabricTest) throws SQLException
    {
        String GET_LOGIN_ID="SELECT idLogin FROM logins where name=(?)";
        String GET_TEST_ID="SELECT idTest FROM tests where name=(?)";
        String ADD_LOGIN="INSERT INTO logins (name) VALUES (?)";
        String ADD_TEST="INSERT INTO tests (name) VALUES (?)";
        String CREATE_NEW_RESULT="INSERT INTO results" +
                "(loginId, testId, dat, mark) VALUES (?,?,?,?)";

        PreparedStatement getLoginId=null;
        PreparedStatement getTestId=null;
        PreparedStatement addLogin=null;
        PreparedStatement addTest=null;
        PreparedStatement createNewResult=null;

        try
        {
            Connection connection=ConnectionDb.getConnection();

            getLoginId=connection.prepareStatement(GET_LOGIN_ID);
            getTestId=connection.prepareStatement(GET_TEST_ID);
            addLogin=connection.prepareStatement(ADD_LOGIN, Statement.RETURN_GENERATED_KEYS);
            addTest=connection.prepareStatement(ADD_TEST, Statement.RETURN_GENERATED_KEYS);
            createNewResult=connection.prepareStatement(CREATE_NEW_RESULT);
            IFileReader reader = fabricTest.getReader();

            while (reader.hasNext())
            {
                AbstractTest readTest=reader.getTest();
                int idLogin=getId(getLoginId,addLogin,readTest.getLogin());
                int idTest=getId(getTestId,addTest,readTest.getName());
                createNewResult.setInt(1,idLogin);
                createNewResult.setInt(2,idTest);
                createNewResult.setDate(3,readTest.getDate());
                createNewResult.setInt(4,readTest.getMark());
                //createNewResult.executeUpdate(); //TODO Not save to db for test
            }
            reader.close();

        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionDb.closeStatement(getLoginId,getTestId,addLogin,addTest,createNewResult);
        }
    }

    private static void clearDB(Connection connection)
    {
        final String CLEAR_RESULTS="DELETE FROM RESULTS";
        PreparedStatement clear=null;

        try
        {
            clear=connection.prepareStatement(CLEAR_RESULTS);
            clear.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionDb.closeStatement(clear);
        }
    }



}
