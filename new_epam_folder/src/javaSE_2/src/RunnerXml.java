package javaSE_2.src;


import javaSE_2.src.by.gsu.epamlab.bll.RunnerLogic;
import javaSE_2.src.by.gsu.epamlab.bll.fabric.FabricDecimalTest;
import javaSE_2.src.by.gsu.epamlab.model.IFabricTest;

public class RunnerXml
{
    public static void main(String[] args)
    {
        final  String fileName="students.xml";

        IFabricTest fabricTest=new FabricDecimalTest(fileName);
        RunnerLogic.logic(fabricTest);
    }
}
