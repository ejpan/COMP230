import java.util.*;
import java.io.*;
public class PhoneDirectory
{
    private ArrayList<DirectoryEntry> theDirectory = new ArrayList<DirectoryEntry>();
    Scanner kb = new Scanner(System.in);

    public String addOrChangeEntry(String name, String number)
    {
        System.out.print("Would you like to add or change an entry?\n1. Add Entry\n2. Change Entry\n");
        String ans = kb.nextLine();
        if(ans.equals("1"))
        {
            theDirectory.add(new DirectoryEntry(name,number));
        }
        else
        {
            System.out.print("Are you changing an entries name or number?\n1. Name\n2. Number\n");
            ans = kb.nextLine();
            if(ans.equals("1"))
            {
                int index = theDirectory.indexOf(new DirectoryEntry(name,""));
                theDirectory.get(index).setName(name);
            }
            else
            {
                int index = theDirectory.indexOf(new DirectoryEntry("",number));
                theDirectory.get(index).setNumber(number);
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
            System.out.println(theDirectory.get(i).getName() + ":" + theDirectory.get(i).getNumber);
        }
    }

    public void fromFile(String fileName)
    {
        for (int i = 0; i < theDirectory.size(); i++){
            theDirectory.set(i,null);
        
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while((line = reader.readLine())!= null)
            {
                String name = line;
                if((line = reader.readLine())!= null){
                    line = reader.readLine();
                    String number = line;
                    theDirectory.add(new DirectoryEntry(name, number));
                }
            }
            reader.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

        }
    }

    public void toFile(String fileName)
    {
        File out = new File(fileName);
        PrintWriter pw = new PrintWriter(new FileOutputStream(out));
        for (int i = 0; i < theDirectory.size(); i++){
            pw.println(theDirectory.get(i).getName());
            pw.println(theDirectory.get(i).getNumber());
        pw.close();
        }
    }


}
