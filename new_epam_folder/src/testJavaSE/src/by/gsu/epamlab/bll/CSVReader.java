package testJavaSE.src.by.gsu.epamlab.bll;

import testJavaSE.src.by.gsu.epamlab.model.CreateNewRowResults;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.Scanner;

public class CSVReader
{
    private CreateNewRowResults createNewRowResults;

    public CSVReader(CreateNewRowResults testAction)
    {
        this.createNewRowResults =testAction;
    }

    public  void readFromFile(String filename) throws  SQLException
    {
        Scanner scanner=null;

        try
        {
            scanner=new Scanner(new FileReader(filename));
            while (scanner.hasNext())
            {
                String readRow=scanner.nextLine();
                createNewRowResults.setAction(readRow);
            }

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(scanner!=null)
            {
                scanner.close();
            }
        }
    }


}
