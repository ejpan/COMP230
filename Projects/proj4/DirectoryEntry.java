import java.util.*;
import java.io.*;
public class DirectoryEntry 
{
    private String name;
    private String number; 

    public DirectoryEntry(String n1, String n2)
    {
       this.name = n1;
       this.number = n2;
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

    
    @Override public boolean equals(Object o)
    {
        DirectoryEntry directory_obj = (DirectoryEntry)o;
        if(directory_obj.getName().equals(this.name))
        {
            return true;
        }
        else if (directory_obj.getName().equals(""))
        {
            if(directory_obj.getNumber().equals(this.number))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if (directory_obj.getNumber().equals(""))
        {
            if(directory_obj.getName().equals(this.name))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }
}
