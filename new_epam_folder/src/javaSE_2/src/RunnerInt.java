package javaSE_2.src;

import javaSE_2.src.by.gsu.epamlab.bll.RunnerLogic;
import javaSE_2.src.by.gsu.epamlab.bll.fabric.FabricIntTest;
import javaSE_2.src.by.gsu.epamlab.model.IFabricTest;

public class RunnerInt
{
    public static void main(String[] args)
    {
        final  String fileName="src/in.csv";

        IFabricTest fabricTest=new FabricIntTest(fileName);
        RunnerLogic.logic(fabricTest);
    }
}
