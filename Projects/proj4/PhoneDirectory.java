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

    public String addOrChangeEntry(int state)
    {
        
        if(state == 1)
        {
            System.out.print("Enter name for the entry: ");
            String name = kb.nextLine();
            System.out.print("Enter the number for the entry: ");
            String number = kb.nextLine();
            theDirectory.add(new DirectoryEntry(name,number));
        }
        else if(state == 2)
        {
            System.out.print("Are you changing an entries name or number?\n1. Name\n2. Number\n");
            int ans = kb.nextInt();
            String empty_space = kb.nextLine();

            
            if(ans == 1)
            {
                System.out.println("Enter name for the entry you want to change: ");
                String initial_name = kb.nextLine(); //This is not going through. program completely skips this line of code
                System.out.println("Enter new name: ");
                String new_name= kb.nextLine();
                int index = theDirectory.indexOf(new DirectoryEntry(initial_name,""));
                theDirectory.get(index).setName(new_name);
            }
            else if (ans == 2)
            {
                System.out.println("Enter number for the entry you want ot change: ");
                String initial_number = kb.nextLine();
                System.out.println("Enter new number: ");
                String new_number = kb.nextLine();
                int index = theDirectory.indexOf(new DirectoryEntry("",initial_number));
                theDirectory.get(index).setNumber(new_number);
            } else
            {
                System.out.println("Error");
            }

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
        return null;
    }

    public DirectoryEntry removeEntry(String name)
    {
        int index = theDirectory.indexOf(new DirectoryEntry(name,""));
        System.out.println(index);
        if(index != -1)
        {
            return theDirectory.remove(index);
        }
        return null;
    }

    public void displayAllEntries()
    {
        for(int i = 0; i < theDirectory.size(); i++)
        {
            System.out.println(theDirectory.get(i).getName() + ": " + theDirectory.get(i).getNumber());
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
                theDirectory.add(new DirectoryEntry(name, number));
                }
            reader.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

        
    }

    public void toFile(String fileName)
    {
        File out = new File(fileName);
        try{
            PrintWriter pw = new PrintWriter(new FileOutputStream(out));
            for (int i = 0; i < theDirectory.size(); i++){
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



