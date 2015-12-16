package javaSE_2.src.by.gsu.epamlab.model;


public interface IFileReader
{
    AbstractTest getTest();
    boolean hasNext();
    void close();
}
