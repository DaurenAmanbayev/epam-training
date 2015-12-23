package javaSE_2.src.by.gsu.epamlab.bll.readers;

import javaSE_2.src.by.gsu.epamlab.model.AbstractTest;
import javaSE_2.src.by.gsu.epamlab.model.IFabricTest;
import javaSE_2.src.by.gsu.epamlab.model.IFileReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ReaderCSV implements IFileReader
{
    private Scanner scanner;
    private IFabricTest fabricTest;

    public ReaderCSV(IFabricTest fabricTest)
    {
        this.fabricTest = fabricTest;
        scanner=null;
        try
        {
            scanner=new Scanner(new FileReader(fabricTest.getFileName()));
        }
        catch (FileNotFoundException e)
        {//TODO
            System.err.println("File is not found, therefor new date didn't load");
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
