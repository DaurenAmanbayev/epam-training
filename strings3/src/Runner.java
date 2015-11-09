import Bll.AbstractReplace;
import Bll.DateReplace;
import Bll.MoneyReplace;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by User on 09.11.2015.
 */
public class Runner
{
    public static void main(String[] args)
    {
        final  String FILE_NAME="src/in.txt";
        Scanner scanner;
        AbstractReplace rrr=new DateReplace();
        AbstractReplace fff=new MoneyReplace();

        try
        {
            scanner=new Scanner(new FileReader(FILE_NAME));

            while (scanner.hasNext())
            {

                String readRow=scanner.nextLine();
                fff.replace(readRow);

                //System.out.println(rrr.replace(readRow));

            }

        } catch (FileNotFoundException e)
        {
            System.err.println("File not found");
        }
    }
}
