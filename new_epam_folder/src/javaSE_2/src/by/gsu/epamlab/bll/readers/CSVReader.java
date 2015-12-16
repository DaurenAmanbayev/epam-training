package javaSE_2.src.by.gsu.epamlab.bll.readers;

import javaSE_2.src.by.gsu.epamlab.model.AbstractTest;
import javaSE_2.src.by.gsu.epamlab.model.IFabricTest;
import javaSE_2.src.by.gsu.epamlab.model.IFileReader;
import testJavaSE.src.by.gsu.epamlab.model.CreateNewRowResults;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.Scanner;

public class CSVReader implements IFileReader
{
    private Scanner scanner;
    private IFabricTest fabricTest;

    public CSVReader(IFabricTest fabricTest)
    {
        this.fabricTest = fabricTest;
        try
        {
            scanner=new Scanner(new FileReader(fabricTest.getFileName()));
        }
        catch (FileNotFoundException e)
        {//TODO
            e.printStackTrace();
        }
    }

    @Override
    public AbstractTest getTest()
    {
        String SEPARATOR=";";
        int LOGIN=0;
        int NAME=1;
        int DATE=2;
        int MARK=3;
        AbstractTest result=null;
        if(hasNext())
        {
            String nextRow=scanner.nextLine();
            String[] nextRows=nextRow.split(SEPARATOR);
            result=fabricTest.getTestFromFile(nextRows[LOGIN],nextRows[NAME],nextRows[DATE],nextRows[MARK]);
        }
        return result;
    }

    @Override
    public boolean hasNext()
    {
        return scanner != null && scanner.hasNext();
    }

    @Override
    public void close()
    {
        if(scanner!=null)
        {
            scanner.close();
        }

    }
}
