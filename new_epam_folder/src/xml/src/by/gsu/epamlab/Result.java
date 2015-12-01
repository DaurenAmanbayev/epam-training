package xml.src.by.gsu.epamlab;


import java.util.ArrayList;
import java.util.List;

public class Result
{
    private String login="";
    private List<Test> test;

    public Result()
    {

        this.test = new ArrayList<>();
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public void addTest(Test test)
    {
        this.test.add(test);
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder=new StringBuilder(this.login)
                .append("\n");
        for (Test tmp:test)
        {
            stringBuilder.append(tmp);
        }

        return stringBuilder.toString();
    }
}
