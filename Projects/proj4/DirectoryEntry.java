/* Class called DirectoryEntry used to store the name and number of a person in a phone directory program.
Each person will be an element as an object in an ArrayList.
Eric Pan & Gabe Seidl
April 20 ,2022
*/

import java.util.*;
import java.io.*;
// Class called DirectoryEtnry
public class DirectoryEntry 
{
    private String name;
    private String number; 
    // Stores name and number as instance variables
    public DirectoryEntry(String n1, String n2)
    {
       this.name = n1;
       this.number = n2;
    } 
    // Returns the name
    public String getName()
    {
        return this.name;
    }
    // Returns number
    public String getNumber()
    {
        return this.number;
    }
    // Assigns name of object to the parameter variable newName
    public void setName(String newName)
    {
        name = newName;
    }
    // Assigns number of object to the parameter variable newNum
    public void setNumber(String newNum)
    {
        number = newNum;
    }

    // Overrides equal method to compare the two instance variables in Indexof method of superclass Object
    @Override public boolean equals(Object o)
    {
        // Down casts the Object class to DirectoryEntry class
        DirectoryEntry directory_obj = (DirectoryEntry)o;
        // Checks if input parameter object's name and number variable is in the directoryentry Arraylist elements
        if(directory_obj.getName().equals(this.name) || directory_obj.getNumber().equals(this.number))
        {
            return true;
        }
        else 
        {
           return false;
        }
    }
}
