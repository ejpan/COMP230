import java.util.*;
import java.io.*;
public class DirectoryTester 
{
    public static void main(String[] args) 
    {
        String name;
        String number;
        String fileName;
        String response = "";
        Scanner kb = new Scanner(System.in);
        PhoneDirectory phoneBook = new PhoneDirectory();
        while(!response.equals("7"))
        {
            System.out.println("What would you like to do?");
            System.out.println("1. Load a previously saved phone directory from file");
            System.out.println("2. Add or change a directory");
            System.out.println("3. Remove an entry");
            System.out.println("4. Search for an entry");
            System.out.println("5. Display all entries");
            System.out.println("6. Save the current phone directory to a file");
            System.out.println("7. Quit the program");
            System.out.print("Select option: ");
            response = kb.nextLine();
            System.out.println("");
            switch (response)
            {
                case "1":
                    System.out.print("Enter the name of the file: ");
                    fileName = kb.nextLine();
                    phoneBook.fromFile(fileName);
                    System.out.println("");
                    break;
                case "2":
                    System.out.println("Enter intended name and number of entry. If an entry already exists you will be asked if you want to update it.");
                    System.out.print("Enter the name of the entry: ");
                    name = kb.nextLine();
                    System.out.print("Enter the number of the entry: ");
                    number = kb.nextLine();
                    String old_num = phoneBook.addOrChangeEntry(name, number);
                    System.out.println("");
                    break;
                case "3":
                    System.out.print("Enter name of the entry: ");
                    name = kb.nextLine();
                    DirectoryEntry de = phoneBook.removeEntry(name);
                    if(de != null)
                    {
                        System.out.println("The entry " + de.getName() + ": " + de.getNumber() + " has been removed.");
                    }
                    System.out.println("");
                    break;
                case "4":
                    System.out.print("Enter name of the entry: ");
                    name = kb.nextLine();
                    DirectoryEntry found = phoneBook.searchEntry(name);
                    if(found != null)
                    {
                        System.out.println("The entry matching " + name + " is " + found.getName() + ": " + found.getNumber());
                    }
                    System.out.println("");
                    break;
                case "5":
                    phoneBook.displayAllEntries();
                    System.out.println("");
                    break;
                case "6":
                    System.out.print("Enter the name of the file: ");
                    fileName = kb.nextLine();
                    phoneBook.toFile(fileName);
                    System.out.println("");
                    break;
                case "7":
                    break;
            }
    
        }
        kb.close();
    }
}
