package javaSE_2.src;


import javaSE_2.src.by.gsu.epamlab.bll.RunnerLogic;
import javaSE_2.src.by.gsu.epamlab.bll.fabric.FabricCSVThreadTest;
import javaSE_2.src.by.gsu.epamlab.bll.fabric.FabricXMLThreadTest;
import javaSE_2.src.by.gsu.epamlab.model.IFabricTest;

public class RunnerTread
{
    public static void main(String[] args)
    {
        final  String fileName="src/in1.csv";
        //final  String fileName="src/xml/students1.xml";

        IFabricTest fabricTest=new FabricCSVThreadTest(fileName);
        RunnerLogic.logic(fabricTest);
    }
}
