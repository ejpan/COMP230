import java.util.*;
import java.io.*;
public class PhoneDirectory
{
    private ArrayList<DirectoryEntry> theDirectory;
    Scanner kb = new Scanner(System.in);

    public PhoneDirectory()
    {
        theDirectory = new ArrayList<DirectoryEntry>();
    }

    public ArrayList<DirectoryEntry> getDirectory()
    {
        return theDirectory;
    }

    public String addOrChangeEntry(String name, String number)
    {
        if(name.equals("") || number.equals(""))
        {
            System.out.println("Name or number empty, invalid entry. Try again.");
            return null;
        }
        DirectoryEntry de = new DirectoryEntry(name, number);
        int index = theDirectory.indexOf(de);
        if(index != -1)
        {
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
            theDirectory.add(de);
            return null;
        }
        return null;
    }

    public DirectoryEntry searchEntry(String name)
    {
        int index = theDirectory.indexOf(new DirectoryEntry(name,""));
        if(index != -1)
        {
            return theDirectory.get(index);
        }
        else
        {
            System.out.println("The entry does not exist.");
        }
        return null;
    }

    public DirectoryEntry removeEntry(String name)
    {
        int index = theDirectory.indexOf(new DirectoryEntry(name,""));
        if(index != -1)
        {
            return theDirectory.remove(index);
        }
        else
        {
            System.out.println("The entry does not exist.");
        }
        return null;
    }

    public void displayAllEntries()
    {
        if(theDirectory.size() > 0)
        {
            for(int i = 0; i < theDirectory.size(); i++)
            {
                System.out.println(theDirectory.get(i).getName() + ": " + theDirectory.get(i).getNumber());
            }
        }
        else
        {
            System.out.println("The directory is empty.");
        }
    }

    public void fromFile(String fileName)
    {
        theDirectory.clear();
        String line;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while((line = reader.readLine())!= null)
            {
                String name = line;
                line = reader.readLine();
                String number = line;
                DirectoryEntry de = new DirectoryEntry(name, number);
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

    public void toFile(String fileName)
    {
        File out = new File(fileName);
        PrintWriter pw;
        try{
            if(out.exists())
            {
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



