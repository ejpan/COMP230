/* Methods for various functions of a Phone Directory Program which includes loading a previously saved phone directory
from existing file, add or change an entry, remove an entry, search an entry, display all entries, save the current
phone directory to a file, and quit the program
Eric Pan & Gabe Seidl
April 20, 2022
*/

import java.util.*;
import java.io.*;
// Phone Directory Class
public class PhoneDirectory
{
    // initializes ArrayList to store elements as objects of the DirectoryEntry class
    private ArrayList<DirectoryEntry> theDirectory;
    Scanner kb = new Scanner(System.in);
    
    //Creates ArrayList to store names and numbers
    public PhoneDirectory()
    {
        theDirectory = new ArrayList<DirectoryEntry>();
    }
    // Do we need this?
    public ArrayList<DirectoryEntry> getDirectory()
    {
        return theDirectory;
    }
    
    // Method to add or change an entry in directory
    public String addOrChangeEntry(String name, String number)
    {
        // If name or number is empty
        if(name.equals("") || number.equals(""))
        {
            System.out.println("Name or number empty, invalid entry. Try again.");
            return null;
        }
        // Creates new DirectoryEntry object 
        DirectoryEntry de = new DirectoryEntry(name, number);
        // Gets index of Directory object. The equals method is overridden and will check if either name or number is in Directory Arraylist.
        int index = theDirectory.indexOf(de);
        // If index does not equal to -1, then the object is in Directory ArrayList.
        // Adds the entry if neither name or number is in Directory ArrayList.
        if(index != -1)
        {
            // Asks if user wants to change the entry
            System.out.println("There already exits a entry with that name or number: " + theDirectory.get(index).getName() + ": " + theDirectory.get(index).getNumber());
            System.out.print("Would you like to change the entry? (y for yes, n for no): ");
            String ans = kb.nextLine();
            if (ans.toLowerCase().equals("y"))
            {
                System.out.println("The entry has been updated.");
                return theDirectory.set(index, de).getNumber();
            }
            else
            {
                System.out.println("The entry was not changed.");
            }
        }
        else
        {
            // Adds entry to directory Arraylist
            theDirectory.add(de);
            return null;
        }
        return null;
    }

    // Method to search for an entry using a name
    public DirectoryEntry searchEntry(String name)
    {
        // Gets index of entry using name
        int index = theDirectory.indexOf(new DirectoryEntry(name,""));
        // If index doens't equal to -1, entry is in Directory Arraylist
        if(index != -1)
        {
            // Adds entry
            return theDirectory.get(index);
        }
        else
        {
            System.out.println("The entry does not exist.");
        }
        return null;
    }

    // Method to remove an entry using a name
    public DirectoryEntry removeEntry(String name)
    {
        // Gets index of entry using name
        int index = theDirectory.indexOf(new DirectoryEntry(name,""));
        // If index doens't equal to -1, entry is in Directory Arraylist
        if(index != -1)
        {
            // Removes entry
            return theDirectory.remove(index);
        }
        else
        {
            System.out.println("The entry does not exist.");
        }
        return null;
    }

    // Prints out entire Directory
    public void displayAllEntries()
    {
        if(theDirectory.size() > 0)
        {
            // Iterates through all entries in Directory Arraylist
            for(int i = 0; i < theDirectory.size(); i++)
            {
                // Prints out name and number of an entry
                System.out.println(theDirectory.get(i).getName() + ": " + theDirectory.get(i).getNumber());
            }
        }
        else
        {
            System.out.println("The directory is empty.");
        }
    }

    // Reads a file containing entries
    public void fromFile(String fileName)
    {
        // Clears current Directory ArrayList
        theDirectory.clear();
        String line;
        try{
            // Reads file
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while((line = reader.readLine())!= null)
            {
                // Reads line containing a name and assigns to variable name
                String name = line;
                // Reads next containing a number and assigns to variable number
                line = reader.readLine();
                String number = line;
                DirectoryEntry de = new DirectoryEntry(name, number);
                // Gets index of entry to check if it is in the Directory ArrayList
                int index = theDirectory.indexOf(de);
                if(index == -1)
                {
                    theDirectory.add(de);
                }
                else
                {
                    theDirectory.set(index, de);
                }
            }
            reader.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

        
    }

    // Appends existing entries to a file, over writes existing entries in a file, or creates a new file for the existing entries
    public void toFile(String fileName)
    {  
        // Reads File
        File out = new File(fileName);
        PrintWriter pw;
        try{
            if(out.exists())
            {
                // If file exists, asks if user wants to append or over write the file
                System.out.print("That file exists, would you like to append? (y for yes, n for no): ");
                String ans = kb.nextLine();
                if(ans.toLowerCase().equals("y"))
                {
                    pw = new PrintWriter(new FileOutputStream(out, true));
                }
                else
                {
                    pw = new PrintWriter(new FileOutputStream(out));

                }
            }
            else
            {
                pw = new PrintWriter(new FileOutputStream(out));
            }
            // Iterates through entire Directory ArrayList and writes it to file
            for (int i = 0; i < theDirectory.size(); i++)
            {
                pw.println(theDirectory.get(i).getName());
                pw.println(theDirectory.get(i).getNumber());
            }
            pw.close();
        }catch(FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        
    }
    
}



