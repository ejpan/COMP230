import java.util.*;
import java.io.*;
public class DirectoryEntry 
{
    private String name;
    private String number; 

    public DirectoryEntry(String n1, String n2)
    {
       name = n1;
       number = n2;
    } 

    public String getName()
    {
        return this.name;
    }

    public String getNumber()
    {
        return this.number;
    }

    public void setName(String newName)
    {
        name = newName;
    }

    public void setNumber(String newNum)
    {
        number = newNum;
    }
}
