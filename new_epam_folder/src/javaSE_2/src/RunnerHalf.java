package javaSE_2.src;


import javaSE_2.src.by.gsu.epamlab.bll.RunnerLogic;
import javaSE_2.src.by.gsu.epamlab.bll.fabric.FabricHalfTest;
import javaSE_2.src.by.gsu.epamlab.model.IFabricTest;

public class RunnerHalf
{
    public static void main(String[] args)
    {
        final  String fileName="in.csv";

        IFabricTest fabricTest=new FabricHalfTest(fileName);
        RunnerLogic.logic(fabricTest);
    }
}
