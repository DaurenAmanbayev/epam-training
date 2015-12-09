package testJavaSE.src.by.gsu.epamlab.bll;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.Scanner;

public class CSVReader
{
    private NewTestAction newTestAction;

    public CSVReader(NewTestAction testAction)
    {
        this.newTestAction=testAction;
    }

    public  void readFromFile(String filename) throws FileNotFoundException, SQLException
    {
        final String SEPARATOR=";";
        Scanner scanner=null;

        try
        {
            scanner=new Scanner(new FileReader(filename));
            while (scanner.hasNext())
            {
                String readRow=scanner.nextLine();
                String[] readRows=readRow.split(SEPARATOR);
                newTestAction.setAction(readRows);
            }

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
