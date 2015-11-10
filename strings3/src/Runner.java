import Bll.DateReplace;
import Bll.MoniesReplace;

import java.io.*;
import java.util.Scanner;

/**
 * Created by User on 09.11.2015.
 */
public class Runner
{
    final static  String FILE_NAME="src/in.txt";
    final static String FILE_OUT_NAME="src/out.txt";
    public static void main(String[] args)
    {

        Scanner scanner=null;
        Writer writer=null;

        try
        {
            scanner=new Scanner(new FileReader(FILE_NAME));
            writer=new FileWriter(FILE_OUT_NAME);

            while (scanner.hasNext())
            {

                String readRow=scanner.nextLine();
                String afterDateReplace=DateReplace.replace(readRow);
                String afterMoniesReplace= MoniesReplace.replace(afterDateReplace);
                writer.write(afterMoniesReplace);
                writer.write("\n");

            }
            writer.close();

        } catch (FileNotFoundException e)
        {
            System.err.println("File not found");
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            if (scanner!=null)
            {
                scanner.close();
            }
        }
    }
}
