package testJavaSE.src;



import testJavaSE.src.by.gsu.epamlab.bll.*;
import testJavaSE.src.by.gsu.epamlab.bll.MySaxParser;
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
    public static String TEST_VERSION ="TASK2";
    //public static String FILE_NAME="src/in.csv";
    public static String FILE_NAME="src/xml/students.xml";

    public static void main(String[] args) throws FileNotFoundException
    {
        try
        {
            Connection connection= ConnectionDb.getConnection();

            final SQLQuerys sqlQuerys=new SQLQuerys(connection);

            ReaderFromFileAndSaveToDB reader=new ReaderFromFileAndSaveToDB(sqlQuerys,FILE_NAME,TEST_VERSION);

            reader.readAndSave();

            List<AbstractTest> listMonth=sqlQuerys.getResultTestAtCurrentMonth(TEST_VERSION);
            for(AbstractTest tmp:listMonth)
            {
                System.out.println(tmp);
            }

        } catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

    }
}
