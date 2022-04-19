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
            response = kb.nextLine();
            switch (response)
            {
                case "1":
                    System.out.print("Enter the name of the file: ");
                    fileName = kb.nextLine();
                    phoneBook.fromFile(fileName);
                    break;
                case "2":
                    System.out.print("Would you like to add or change an entry?\n1. Add Entry\n2. Change Entry\n");
                    int state = kb.nextInt();
                    String white_space = kb.nextLine(); //gets rid of whitespace after getInt()
                    phoneBook.addOrChangeEntry(state);
                    break;
                case "3":
                    System.out.print("Enter name of the entry: ");
                    name = kb.nextLine();
                    phoneBook.removeEntry(name);
                    break;
                case "4":
                    System.out.print("Enter name of the entry: ");
                    name = kb.nextLine();
                    System.out.println("The index of the entry is " + phoneBook.searchEntry(name));
                    break;
                case "5":
                    phoneBook.displayAllEntries();
                    break;
                case "6":
                    System.out.print("Enter the name of the file: ");
                    fileName = kb.nextLine();
                    phoneBook.toFile(fileName);
                    break;
                case "7":
                    break;
            }
    
        }
        kb.close();
    }
}
