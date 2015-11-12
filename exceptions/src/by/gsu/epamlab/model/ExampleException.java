package by.gsu.epamlab.model;


public class ExampleException extends Exception
{
    public enum StringException

        {NON_POSITIVE(" non positive"),
            NOT_PARSE(" isn`t parsed"),
            EMPTY(" is empty"),
            WRONG_NUMBER("Wrong number argument"),
            NON_CORRECT(" non correct data");
            private String text;

            StringException(String s)
            {
                this.text=s;
            }
             public String getText()
             {
                 return text;
             }
        }

    public ExampleException(String message,StringException select){

        super(message+select.getText());

    }
    public ExampleException(StringException select){

        super(select.getText());

    }
}
